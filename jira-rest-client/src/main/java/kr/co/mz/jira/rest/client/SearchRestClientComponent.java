package kr.co.mz.jira.rest.client;

import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import java.util.Collections;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import kr.co.mz.support.dto.PageOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Component
@Validated
@RequiredArgsConstructor
public class SearchRestClientComponent {

  private final SearchRestClient searchRestClient;

  public SearchResult loadByJql(
      final @NotBlank String jql,
      final @NotNull PageOptions pageOptions
  ) {
    final var promiseSearchResult = searchRestClient.searchJql(
        jql,
        pageOptions.getPageSize(),
        pageOptions.getPageSize() * pageOptions.getPageNumber(),
        Set.of("*navigable")
    );

    log.info("fetch SearchResult: {}", pageOptions);

    return promiseSearchResult.claim();
  }
}
