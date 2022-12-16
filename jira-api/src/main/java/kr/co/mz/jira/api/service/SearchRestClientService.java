package kr.co.mz.jira.api.service;

import com.atlassian.jira.rest.client.api.domain.SearchResult;
import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.api.client.SearchRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SearchRestClientService {

  private final SearchRestClientProvider searchRestClientProvider;

  @SuppressWarnings("UnstableApiUsage")
  public SearchResult loadByJql(final @NotBlank String jql) {
    final var searchRestClient = searchRestClientProvider.get();
    final var promiseSearchResult = searchRestClient.searchJql(jql);

    return promiseSearchResult.claim();
  }
}
