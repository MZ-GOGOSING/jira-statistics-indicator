package kr.co.mz.jira.api.credential;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder
public class DefaultJiraCredential implements JiraCredential {

  private final String username;

  private final String password;

  private final String jiraUrl;

  private DefaultJiraCredential(
      final String username,
      final String password,
      final String jiraUrl
  ) {
    Assert.state(isNotBlank(username), "username 은 필수 입니다.");
    Assert.state(isNotBlank(password), "password 는 필수 입니다.");
    Assert.state(isNotBlank(jiraUrl), "Jira Url 은 필수 입니다.");

    this.username = username;
    this.password = password;
    this.jiraUrl = jiraUrl;
  }
}
