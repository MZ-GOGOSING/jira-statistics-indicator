package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.GetSubjectDetailQuery;
import kr.co.mz.jira.application.port.in.response.GetSubjectDetailInResponse;
import kr.co.mz.jira.application.port.out.LoadAllIssueItemPort;
import kr.co.mz.jira.application.port.out.LoadSubjectItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetSubjectDetailService implements GetSubjectDetailQuery {

  private final LoadSubjectItemPort loadSubjectItemPort;

  private final LoadAllIssueItemPort loadAllDefaultIssueItemPort;

  @Override
  public GetSubjectDetailInResponse loadByUuid(final String uuid) {
    final var subjectDomainEntity = loadSubjectItemPort.findByUuid(uuid);
    final var issueDomainEntities =
        loadAllDefaultIssueItemPort.findAllBySubjectId(subjectDomainEntity.getId());

    return GetSubjectDetailInResponse.of(subjectDomainEntity, issueDomainEntities);
  }
}
