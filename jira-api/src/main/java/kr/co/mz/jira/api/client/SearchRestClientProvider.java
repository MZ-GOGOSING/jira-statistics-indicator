package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.SearchRestClient;

public interface SearchRestClientProvider {

  SearchRestClient get();
}
