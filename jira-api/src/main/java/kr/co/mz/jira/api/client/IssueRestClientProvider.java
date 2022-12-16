package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.IssueRestClient;

public interface IssueRestClientProvider {

  IssueRestClient get();
}
