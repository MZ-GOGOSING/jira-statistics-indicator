package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.ProjectRestClient;

public interface ProjectRestClientProvider {

  ProjectRestClient get();
}
