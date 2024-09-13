package kr.co.mz.jira.application;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.application.port.in.SyncIssuesUseCase;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.application.port.out.LoadSubjectPort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.code.ChunkSize;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SyncIssuesService implements SyncIssuesUseCase {

  private final LoadSubjectPort loadSubjectPort;

  private final FetchAllIssuePort fetchAllIssuePort;

  private final CreateAllIssuePort createAllIssuePort;

  @Override
  public SyncSearchResultInResponse sync(final Long subjectId) {
    final var subjectDomainEntity = this.loadSubjectById(subjectId);
    final var issueDomainEntities = this.saveAllIssues(
        subjectDomainEntity.getId(), subjectDomainEntity.getIssueKeyList()
    );

    return SyncSearchResultInResponse.of(subjectDomainEntity, issueDomainEntities);
  }

  private SubjectDomainEntity loadSubjectById(final Long subjectId) {
    return loadSubjectPort.findById(subjectId);
  }

  private List<IssueDomainEntity> saveAllIssues(
      final Long subjectId,
      final List<String> issueKeyList
  ) {
    if (CollectionUtils.isEmpty(issueKeyList)) {
      return Collections.emptyList();
    }

    return ListUtils.partition(issueKeyList, ChunkSize.C_5.getPageSize())
        .stream()
        .map(chunkedIssueKeyList -> this.saveAllIssueList(subjectId, chunkedIssueKeyList))
        .filter(CollectionUtils::isNotEmpty)
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  private List<IssueDomainEntity> saveAllIssueList(
      final Long subjectId,
      final List<String> issueKeyList
  ) {
    try {
      final var fetchedIssueDomainEntities = fetchAllIssuePort.fetchAllByIssueKeyList(issueKeyList);

      final var outCommand = CreateAllIssueOutCommand.builder()
          .subjectId(subjectId)
          .issueDomainEntities(fetchedIssueDomainEntities)
          .build();

      return createAllIssuePort.saveAll(outCommand);
    } catch (RuntimeException runtimeException) {
      log.error("issue 를 동기화 하던 중 오류 발생", runtimeException);
    }
    return Collections.emptyList();
  }
}
