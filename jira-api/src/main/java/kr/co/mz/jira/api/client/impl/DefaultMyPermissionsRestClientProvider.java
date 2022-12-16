package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.MyPermissionsRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.MyPermissionsRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultMyPermissionsRestClientProvider implements MyPermissionsRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public MyPermissionsRestClient get() {
    return jiraRestClientProvider.get().getMyPermissionsRestClient();
  }
}
