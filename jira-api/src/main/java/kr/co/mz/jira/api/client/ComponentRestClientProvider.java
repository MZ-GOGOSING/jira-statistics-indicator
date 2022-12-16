package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.ComponentRestClient;

public interface ComponentRestClientProvider {

  ComponentRestClient get();
}
