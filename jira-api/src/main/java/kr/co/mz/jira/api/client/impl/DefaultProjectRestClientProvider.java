package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.ProjectRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.ProjectRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"defaultJiraRestClientProvider"})
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultProjectRestClientProvider implements ProjectRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public ProjectRestClient get() {
    return jiraRestClientProvider.get().getProjectClient();
  }
}
