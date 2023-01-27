package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.GetSubjectDocumentQuery;
import kr.co.mz.jira.application.port.out.CreateEmptyDocumentPort;
import kr.co.mz.jira.application.port.out.CreateSubjectDocumentPort;
import kr.co.mz.jira.application.port.out.LoadIssueItemsPort;
import kr.co.mz.jira.application.port.out.LoadSubjectItemPort;
import kr.co.mz.support.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetSubjectDocumentService implements GetSubjectDocumentQuery {

  private static final String EMPTY_DOCUMENT_DEFAULT_MESSAGE = "집계 대상 데이터가 존재하지 않습니다.";

  private final LoadSubjectItemPort loadSubjectItemPort;

  private final LoadIssueItemsPort loadStatusFieldContainsIssueItemsPort;

  private final CreateEmptyDocumentPort createEmptyDocumentPort;

  private final CreateSubjectDocumentPort createSubjectDocumentPort;

  @Override
  public byte[] loadByUuid(final String uuid) {
    try {
      final var subjectDomainEntity = loadSubjectItemPort.findByUuid(uuid);
      final var issueDomainEntities = loadStatusFieldContainsIssueItemsPort
          .findAllBySubjectId(subjectDomainEntity.getId());

      return createSubjectDocumentPort.create(issueDomainEntities);
    } catch (EntityNotFoundException entityNotFoundException) {
      return createEmptyDocumentPort.create(EMPTY_DOCUMENT_DEFAULT_MESSAGE);
    }
  }
}
