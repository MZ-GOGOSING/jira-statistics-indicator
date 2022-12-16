package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.MyPermissionsRestClient;

public interface MyPermissionsRestClientProvider {

  MyPermissionsRestClient get();
}
