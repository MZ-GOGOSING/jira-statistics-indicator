package kr.co.mz.jira.rest.credential;

public interface JiraCredential {

  String getUsername();

  String getPassword();

  String getJiraUrl();
}
