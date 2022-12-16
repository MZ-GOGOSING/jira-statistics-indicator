package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import kr.co.mz.jira.api.autoconfigure.JiraCredential;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraCredential.class)
@RequiredArgsConstructor
public class DefaultJiraRestClientProvider implements JiraRestClientProvider {

  private final JiraCredential jiraCredential;

  @Override
  public JiraRestClient get() {
    final var jiraRestClientFactory = new AsynchronousJiraRestClientFactory();
    final var jiraURI = URI.create(jiraCredential.getJiraUrl());

    return jiraRestClientFactory.createWithBasicHttpAuthentication(
        jiraURI,
        jiraCredential.getUsername(),
        jiraCredential.getPassword()
    );
  }
}
