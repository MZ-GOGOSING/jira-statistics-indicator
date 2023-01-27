package kr.co.mz.jira.rest.properties;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jira.client.credential")
public class JiraCredentialProperties {

  @NotBlank
  private final String username;

  @NotBlank
  private final String password;

  @NotBlank
  private final String jiraUrl;
}
