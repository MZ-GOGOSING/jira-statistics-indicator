package kr.co.mz.jira.api.config;

import kr.co.mz.jira.api.autoconfigure.JiraCredential;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JiraCredentialProperties.class)
public class JiraCredentialConfig {

  @Bean
  @ConditionalOnMissingBean
  public JiraCredential jiraCredential(final JiraCredentialProperties jiraCredentialProperties) {
    return new JiraCredential(
        jiraCredentialProperties.getUsername(),
        jiraCredentialProperties.getPassword(),
        jiraCredentialProperties.getJiraUrl()
    );
  }
}
