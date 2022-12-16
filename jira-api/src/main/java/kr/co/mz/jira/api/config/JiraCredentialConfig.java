package kr.co.mz.jira.api.config;

import javax.validation.Valid;
import kr.co.mz.jira.api.credential.DefaultJiraCredential;
import kr.co.mz.jira.api.credential.JiraCredential;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@EnableConfigurationProperties(JiraCredentialProperties.class)
public class JiraCredentialConfig {

  @Bean
  public JiraCredential jiraCredential(final @Valid JiraCredentialProperties properties) {
    return DefaultJiraCredential.builder()
        .username(properties.getUsername())
        .password(properties.getPassword())
        .jiraUrl(properties.getJiraUrl())
        .build();
  }
}
