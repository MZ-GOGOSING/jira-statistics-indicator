package kr.co.mz.jira.adapter.out.web;

import kr.co.mz.jira.adapter.out.web.converter.SubjectDomainEntityConverter;
import kr.co.mz.jira.rest.client.SearchRestClientService;
import kr.co.mz.jira.rest.credential.JiraCredential;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class FetchSearchResultAdapter implements FetchSearchResultPort {

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private final JiraCredential jiraCredential;

  private final SearchRestClientService searchRestClientService;

  @Override
  public SubjectDomainEntity fetchByJql(final String jql) {
    final var searchResult = searchRestClientService.loadByJql(jql);

    return SUBJECT_DOMAIN_ENTITY_CONVERTER.convert(jql, jiraCredential, searchResult);
  }
}
