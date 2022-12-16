package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.ProjectRolesRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.ProjectRolesRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultProjectRolesRestClientProvider implements ProjectRolesRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public ProjectRolesRestClient get() {
    return jiraRestClientProvider.get().getProjectRolesRestClient();
  }
}
