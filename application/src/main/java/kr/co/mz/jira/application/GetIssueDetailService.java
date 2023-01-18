package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.GetIssueDetailQuery;
import kr.co.mz.jira.application.port.in.response.GetIssueDetailInResponse;
import kr.co.mz.jira.application.port.out.LoadIssueItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetIssueDetailService implements GetIssueDetailQuery {

  private final LoadIssueItemPort loadIssueItemPort;

  @Override
  public GetIssueDetailInResponse loadById(final Long id) {
    final var issueDomainEntity = loadIssueItemPort.findById(id);

    return GetIssueDetailInResponse.of(issueDomainEntity);
  }
}
