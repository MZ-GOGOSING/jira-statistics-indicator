package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.AuditRestClient;

public interface AuditRestClientProvider {

  AuditRestClient get();
}
