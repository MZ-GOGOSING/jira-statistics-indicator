package kr.co.mz.jira.adapter.out.api;

import kr.co.mz.jira.api.service.IssueRestClientService;
import kr.co.mz.jira.api.service.SearchRestClientService;
import kr.co.mz.jira.application.port.out.LoadExternalIssuePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class LoadExternalIssueApiAdapter implements LoadExternalIssuePort {

  private final SearchRestClientService searchRestClientService;

  private final IssueRestClientService issueRestClientService;

  @Override
  public void loadAllByJql(final String jql) {
    final var searchResult = searchRestClientService.loadByJql(jql);
    final var issueList = issueRestClientService.loadAllBySearchResult(searchResult);

    final var test = 1;
  }
}
