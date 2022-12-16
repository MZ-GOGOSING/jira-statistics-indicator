package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.VersionRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.VersionRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultVersionRestClientProvider implements VersionRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public VersionRestClient get() {
    return jiraRestClientProvider.get().getVersionRestClient();
  }
}
