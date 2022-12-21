package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import kr.co.mz.jira.api.credential.JiraCredential;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.support.converter.TriConverter;

public class SubjectDomainEntityConverter implements
    TriConverter<String, JiraCredential, SearchResult, SubjectDomainEntity> {

  @Override
  public SubjectDomainEntity convert(
      final String jql,
      final JiraCredential jiraCredential,
      final SearchResult searchResult
  ) {
    final var issueKeyList = StreamSupport
        .stream(searchResult.getIssues().spliterator(), false)
        .map(BasicIssue::getKey)
        .collect(Collectors.toList());

    return SubjectDomainEntity.withoutId(
        UUID.randomUUID().toString(),
        jql,
        issueKeyList,
        jiraCredential.getUsername(),
        LocalDateTime.now()
    );
  }
}
