package kr.co.mz.jira.adapter.out.web;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import kr.co.mz.jira.adapter.out.web.converter.SubjectDomainEntityConverter;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.code.ChunkSize;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.rest.client.SearchRestClientComponent;
import kr.co.mz.jira.rest.credential.JiraCredential;
import kr.co.mz.support.dto.PageOptions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class FetchSearchResultRestAdapter implements FetchSearchResultPort {

  private static final int PAGE_SIZE = ChunkSize.C_5.getPageSize();

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private final JiraCredential jiraCredential;

  private final SearchRestClientComponent searchRestClientComponent;

  @Override
  public SubjectDomainEntity fetchByJql(final String jql) {
    final var issueKeyList = new ArrayList<String>();
    final var searchResult = searchRestClientComponent.loadByJql(jql, PageOptions.defaultOf(PAGE_SIZE));

    this.addAllIssueKeyList(searchResult, issueKeyList);

    if (this.hasNext(searchResult)) {
      this.fetchNextPageByJql(jql, searchResult, issueKeyList);
    }

    final var subjectDomainEntity = SUBJECT_DOMAIN_ENTITY_CONVERTER.convert(jql, jiraCredential, searchResult);

    Collections.sort(issueKeyList);

    subjectDomainEntity.getIssueKeyList().clear();
    subjectDomainEntity.getIssueKeyList().addAll(issueKeyList);

    return subjectDomainEntity;
  }

  private void fetchNextPageByJql(
      final String jql,
      final SearchResult previousSearchResult,
      final List<String> data
  ) {
    final var currentPageNumber = this.getCurrentPageNumber(previousSearchResult.getStartIndex());
    final var pageOptions = new PageOptions(PAGE_SIZE, currentPageNumber);

    final var searchResult = searchRestClientComponent.loadByJql(jql, pageOptions);

    if (!IterableUtils.isEmpty(searchResult.getIssues())) {
      this.addAllIssueKeyList(searchResult, data);
    }

    if (this.hasNext(searchResult)) {
      this.fetchNextPageByJql(jql, searchResult, data);
    }
  }

  private void addAllIssueKeyList(final SearchResult searchResult, final List<String> data) {
    final var issueKeyList = StreamSupport
        .stream(searchResult.getIssues().spliterator(), false)
        .map(BasicIssue::getKey)
        .sorted()
        .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(issueKeyList)) {
      data.addAll(issueKeyList);
    }
  }

  private boolean hasNext(final SearchResult searchResult) {
    final var currentPageNumber = this.getCurrentPageNumber(searchResult.getStartIndex());
    final var totalPageNumber = this.getTotalPageNumber(searchResult.getTotal());

    return currentPageNumber < totalPageNumber;
  }

  private int getCurrentPageNumber(final int startIndex) {
    return (startIndex / PAGE_SIZE) + 1;
  }

  private int getTotalPageNumber(final int total) {
    return (total + PAGE_SIZE - 1) / PAGE_SIZE;
  }
}
