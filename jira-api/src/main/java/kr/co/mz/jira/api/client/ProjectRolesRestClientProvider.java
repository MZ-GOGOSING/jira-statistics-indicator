package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.ProjectRolesRestClient;

public interface ProjectRolesRestClientProvider {

  ProjectRolesRestClient get();
}
