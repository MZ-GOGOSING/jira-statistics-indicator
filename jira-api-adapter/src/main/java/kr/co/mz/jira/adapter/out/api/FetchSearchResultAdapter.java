package kr.co.mz.jira.adapter.out.api;

import kr.co.mz.jira.adapter.out.api.converter.SubjectDomainEntityConverter;
import kr.co.mz.jira.api.credential.JiraCredential;
import kr.co.mz.jira.api.service.SearchRestClientService;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class FetchSearchResultAdapter implements FetchSearchResultPort {

  private final JiraCredential jiraCredential;

  private final SearchRestClientService searchRestClientService;

  @Override
  public SubjectDomainEntity fetchByJql(final String jql) {
    final var searchResult = searchRestClientService.loadByJql(jql);

    return new SubjectDomainEntityConverter().convert(jql, jiraCredential, searchResult);
  }
}
