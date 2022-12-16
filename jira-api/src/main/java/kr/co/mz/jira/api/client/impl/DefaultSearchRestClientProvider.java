package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.SearchRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.SearchRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"defaultJiraRestClientProvider"})
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultSearchRestClientProvider implements SearchRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public SearchRestClient get() {
    return jiraRestClientProvider.get().getSearchClient();
  }
}
