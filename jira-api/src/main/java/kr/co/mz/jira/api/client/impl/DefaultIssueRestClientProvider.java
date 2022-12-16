package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import kr.co.mz.jira.api.client.IssueRestClientProvider;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultIssueRestClientProvider implements IssueRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public IssueRestClient get() {
    return jiraRestClientProvider.get().getIssueClient();
  }
}
