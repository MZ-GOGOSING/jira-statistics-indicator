package kr.co.mz.jira.api.config;

import com.atlassian.jira.rest.client.api.AuditRestClient;
import com.atlassian.jira.rest.client.api.ComponentRestClient;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.MetadataRestClient;
import com.atlassian.jira.rest.client.api.MyPermissionsRestClient;
import com.atlassian.jira.rest.client.api.ProjectRestClient;
import com.atlassian.jira.rest.client.api.ProjectRolesRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.SessionRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.VersionRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousHttpClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import javax.validation.Valid;
import kr.co.mz.jira.api.credential.DefaultJiraCredential;
import kr.co.mz.jira.api.credential.JiraCredential;
import kr.co.mz.jira.api.custom.AsynchronousWorklogRestClient;
import kr.co.mz.jira.api.custom.WorklogRestClient;
import kr.co.mz.jira.api.properties.JiraCredentialProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@EnableConfigurationProperties(JiraCredentialProperties.class)
public class JiraRestClientConfig {

  @Bean
  public JiraCredential jiraCredential(final @Valid JiraCredentialProperties properties) {
    return DefaultJiraCredential.builder()
        .username(properties.getUsername())
        .password(properties.getPassword())
        .jiraUrl(properties.getJiraUrl())
        .build();
  }

  @Bean
  public JiraRestClient jiraRestClient(final JiraCredential jiraCredential) {
    final var jiraRestClientFactory = new AsynchronousJiraRestClientFactory();
    final var jiraURI = this.createJiraURI(jiraCredential);

    return jiraRestClientFactory.createWithBasicHttpAuthentication(
        jiraURI,
        jiraCredential.getUsername(),
        jiraCredential.getPassword()
    );
  }

  @Bean
  public AuditRestClient auditRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getAuditRestClient();
  }

  @Bean
  public ComponentRestClient componentRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getComponentClient();
  }

  @Bean
  public IssueRestClient issueRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getIssueClient();
  }

  @Bean
  public MetadataRestClient metadataRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getMetadataClient();
  }

  @Bean
  public MyPermissionsRestClient myPermissionsRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getMyPermissionsRestClient();
  }

  @Bean
  public ProjectRestClient projectRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getProjectClient();
  }

  @Bean
  public ProjectRolesRestClient projectRolesRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getProjectRolesRestClient();
  }

  @Bean
  public SearchRestClient searchRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getSearchClient();
  }

  @Bean
  public SessionRestClient sessionRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getSessionClient();
  }

  @Bean
  public UserRestClient userRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getUserClient();
  }

  @Bean
  public VersionRestClient versionRestClient(final JiraRestClient jiraRestClient) {
    return jiraRestClient.getVersionRestClient();
  }

  @Bean
  public WorklogRestClient worklogRestClient(final JiraCredential jiraCredential) {
    final var jiraURI = this.createJiraURI(jiraCredential);
    final var authenticationHandler = new BasicHttpAuthenticationHandler(
        jiraCredential.getUsername(),
        jiraCredential.getPassword()
    );

    final var httpClient = new AsynchronousHttpClientFactory()
        .createClient(jiraURI, authenticationHandler);

    return new AsynchronousWorklogRestClient(jiraURI, httpClient);
  }

  private URI createJiraURI(final JiraCredential jiraCredential) {
    return URI.create(jiraCredential.getJiraUrl());
  }
}
