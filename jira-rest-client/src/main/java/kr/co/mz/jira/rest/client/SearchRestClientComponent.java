package kr.co.mz.jira.rest.client;

import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class SearchRestClientComponent {

  private final SearchRestClient searchRestClient;

  public SearchResult loadByJql(final @NotBlank String jql) {
    final var promiseSearchResult = searchRestClient.searchJql(jql);

    return promiseSearchResult.claim();
  }
}
