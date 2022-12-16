package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.JiraRestClient;

public interface JiraRestClientProvider {

  JiraRestClient get();
}
