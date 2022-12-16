package kr.co.mz.jira.api.service;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.api.client.IssueRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class IssueRestClientService {

  private final IssueRestClientProvider issueRestClientProvider;

  @SuppressWarnings("UnstableApiUsage")
  public List<Issue> loadAllBySearchResult(final @NotNull SearchResult searchResult) {
    final var issueRestClient = issueRestClientProvider.get();
    final var issueKeyList = StreamSupport
        .stream(searchResult.getIssues().spliterator(), false)
        .map(BasicIssue::getKey)
        .collect(Collectors.toList());

    return CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .map(issueRestClient::getIssue)
        .map(Promise::claim)
        .collect(Collectors.toList());
  }
}
