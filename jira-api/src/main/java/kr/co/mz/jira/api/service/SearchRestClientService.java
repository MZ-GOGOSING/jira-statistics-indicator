package kr.co.mz.jira.api.service;

import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SearchRestClientService {

  /**
   * 검색어(jql)결과의 조회 총 수량 제한 값.
   * (JIRA SDK 에서 최대 허용 가능한 수량은 1500이다. 만약, 초과할 경우 1500으로 강제 조정된다.)
   */
  private static final Integer FETCH_SEARCH_MAX_RESULT_LIMIT = 1500;

  private final SearchRestClient searchRestClient;

  @SuppressWarnings("UnstableApiUsage")
  public SearchResult loadByJql(final @NotBlank String jql) {
    final var promiseSearchResult = searchRestClient.searchJql(
        jql,
        FETCH_SEARCH_MAX_RESULT_LIMIT,
        null,
        null
    );

    return promiseSearchResult.claim();
  }
}
