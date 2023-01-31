package kr.co.mz.jira.application;

import java.util.List;
import kr.co.mz.jira.application.port.in.GetSubjectDocumentQuery;
import kr.co.mz.jira.application.port.in.request.query.GetSubjectDocumentInQuery;
import kr.co.mz.jira.application.port.out.LoadAllIssueItemPort;
import kr.co.mz.jira.application.port.out.LoadSubjectItemPort;
import kr.co.mz.jira.application.port.out.PublishEmptyDocumentPort;
import kr.co.mz.jira.application.port.out.PublishSubjectDocumentPort;
import kr.co.mz.jira.application.port.out.request.command.PublishSubjectDocumentOutCommand;
import kr.co.mz.jira.code.IssueStatus;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.support.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetSubjectDocumentService implements GetSubjectDocumentQuery {

  private static final String EMPTY_DOCUMENT_DEFAULT_MESSAGE = "집계 대상 데이터가 존재하지 않습니다.";

  private final LoadSubjectItemPort loadSubjectItemPort;

  private final LoadAllIssueItemPort loadAllFieldValueMatchedIssueItemPort;

  private final PublishEmptyDocumentPort publishEmptyDocumentPort;

  private final PublishSubjectDocumentPort publishSubjectDocumentPort;

  @Override
  public byte[] publish(final GetSubjectDocumentInQuery inQuery) {
    try {
      final var subjectDomainEntity = loadSubjectItemPort.findByUuid(inQuery.getUuid());
      final var issueDomainEntities = loadAllFieldValueMatchedIssueItemPort
          .findAllBySubjectId(subjectDomainEntity.getId());

      final var outCommand = this.convertToOutCommand(issueDomainEntities, inQuery.getWorkflow());

      return publishSubjectDocumentPort.publish(outCommand);
    } catch (EntityNotFoundException entityNotFoundException) {
      return publishEmptyDocumentPort.publish(EMPTY_DOCUMENT_DEFAULT_MESSAGE);
    }
  }

  private PublishSubjectDocumentOutCommand convertToOutCommand(
      final List<IssueDomainEntity> issueDomainEntities,
      final List<IssueStatus> workflow
  ) {
    if (CollectionUtils.isEmpty(issueDomainEntities)) {
      throw new EntityNotFoundException();
    }
    return PublishSubjectDocumentOutCommand.builder()
        .issueDomainEntities(issueDomainEntities)
        .workflow(workflow)
        .build();
  }
}
