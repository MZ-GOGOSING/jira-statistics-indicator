package kr.co.mz.jira.application;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import kr.co.mz.jira.application.port.in.SyncSearchResultUseCase;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.CreateAllStatusPort;
import kr.co.mz.jira.application.port.out.CreateSubjectPort;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.application.port.out.FetchAllStatusPort;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.application.port.out.LoadAllStatusPort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SyncSearchResultService implements SyncSearchResultUseCase {

  private final FetchSearchResultPort fetchSearchResultPort;

  private final FetchAllIssuePort fetchAllIssuePort;

  private final CreateSubjectPort createSubjectPort;

  private final CreateAllIssuePort createAllIssuePort;

  private final FetchAllStatusPort fetchAllStatusPort;

  private final CreateAllStatusPort createAllStatusPort;

  private final LoadAllStatusPort loadAllStatusPort;

  @Override
  public SyncSearchResultInResponse sync(final String jql) {
    final var subjectDomainEntity = this.saveSubject(jql);

    final var statusDomainEntities = this.findAllStatuses(
        subjectDomainEntity.getCreatedDate().toLocalDate()
    );

    final var issueDomainEntities = this.saveAllIssues(
        subjectDomainEntity.getId(), subjectDomainEntity.getIssueKeyList()
    );

    return SyncSearchResultInResponse.of(subjectDomainEntity, issueDomainEntities);
  }

  private SubjectDomainEntity saveSubject(final String jql) {
    final var fetchedSubjectDomainEntity = fetchSearchResultPort.fetchByJql(jql);

    return createSubjectPort.save(fetchedSubjectDomainEntity);
  }

  private List<StatusDomainEntity> findAllStatuses(final LocalDate syncDate) {
    final var statusDomainEntities = loadAllStatusPort.findAllBySyncDate(syncDate);

    if (CollectionUtils.isNotEmpty(statusDomainEntities)) {
      return statusDomainEntities;
    }

    final var fetchedStatusDomainEntities = fetchAllStatusPort.fetchAll(syncDate);

    return CollectionUtils.isEmpty(fetchedStatusDomainEntities)
        ? Collections.emptyList()
        : createAllStatusPort.saveAll(fetchedStatusDomainEntities);
  }

  private List<IssueDomainEntity> saveAllIssues(
      final Long subjectId,
      final List<String> issueKeyList
  ) {
    if (CollectionUtils.isEmpty(issueKeyList)) {
      return Collections.emptyList();
    }

    final var fetchedIssueDomainEntities = fetchAllIssuePort.fetchAllByIssueKeyList(issueKeyList);

    final var outCommand = CreateAllIssueOutCommand.builder()
        .subjectId(subjectId)
        .issueDomainEntities(fetchedIssueDomainEntities)
        .build();

    return createAllIssuePort.saveAll(outCommand);
  }
}
