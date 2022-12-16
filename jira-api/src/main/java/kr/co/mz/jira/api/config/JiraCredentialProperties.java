package kr.co.mz.jira.api.config;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "jira.client.credential")
public class JiraCredentialProperties {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String jiraUrl;
}
