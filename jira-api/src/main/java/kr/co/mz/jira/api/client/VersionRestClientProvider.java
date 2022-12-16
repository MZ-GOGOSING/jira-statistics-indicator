package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.VersionRestClient;

public interface VersionRestClientProvider {

  VersionRestClient get();
}
