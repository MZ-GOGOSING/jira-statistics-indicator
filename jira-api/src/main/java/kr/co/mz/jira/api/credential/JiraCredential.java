package kr.co.mz.jira.api.credential;

public interface JiraCredential {

  String getUsername();

  String getPassword();

  String getJiraUrl();
}
