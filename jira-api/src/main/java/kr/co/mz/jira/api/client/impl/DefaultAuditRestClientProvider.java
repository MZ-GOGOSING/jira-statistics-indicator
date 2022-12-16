package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.AuditRestClient;
import kr.co.mz.jira.api.client.AuditRestClientProvider;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"defaultJiraRestClientProvider"})
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultAuditRestClientProvider implements AuditRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public AuditRestClient get() {
    return jiraRestClientProvider.get().getAuditRestClient();
  }
}
