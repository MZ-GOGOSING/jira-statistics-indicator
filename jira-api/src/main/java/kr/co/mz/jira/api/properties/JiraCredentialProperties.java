package kr.co.mz.jira.api.properties;

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
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String jiraUrl;
}
