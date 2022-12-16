package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.credential.JiraCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn({"jiraCredential"})
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
