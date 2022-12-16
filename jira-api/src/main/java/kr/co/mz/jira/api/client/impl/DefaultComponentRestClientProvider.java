package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.ComponentRestClient;
import kr.co.mz.jira.api.client.ComponentRestClientProvider;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"defaultJiraRestClientProvider"})
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultComponentRestClientProvider implements ComponentRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public ComponentRestClient get() {
    return jiraRestClientProvider.get().getComponentClient();
  }
}
