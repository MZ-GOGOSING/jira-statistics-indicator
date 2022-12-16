package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.UserRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.UserRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultUserRestClientProvider implements UserRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public UserRestClient get() {
    return jiraRestClientProvider.get().getUserClient();
  }
}
