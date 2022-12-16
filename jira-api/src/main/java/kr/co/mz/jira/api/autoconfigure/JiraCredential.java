package kr.co.mz.jira.api.autoconfigure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JiraCredential {

  private final String username;

  private final String password;

  private final String jiraUrl;
}
