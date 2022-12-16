package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.UserRestClient;

public interface UserRestClientProvider {

  UserRestClient get();
}
