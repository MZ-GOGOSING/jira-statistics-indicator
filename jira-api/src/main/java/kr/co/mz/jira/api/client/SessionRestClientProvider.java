package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.SessionRestClient;

public interface SessionRestClientProvider {

  SessionRestClient get();
}
