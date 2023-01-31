package kr.co.mz.jira.adapter.out.web;

import kr.co.mz.jira.adapter.out.web.converter.SubjectDomainEntityConverter;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.rest.client.SearchRestClientComponent;
import kr.co.mz.jira.rest.credential.JiraCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class FetchSearchResultRestAdapter implements FetchSearchResultPort {

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private final JiraCredential jiraCredential;

  private final SearchRestClientComponent searchRestClientComponent;

  @Override
  public SubjectDomainEntity fetchByJql(final String jql) {
    final var searchResult = searchRestClientComponent.loadByJql(jql);

    return SUBJECT_DOMAIN_ENTITY_CONVERTER.convert(jql, jiraCredential, searchResult);
  }
}
