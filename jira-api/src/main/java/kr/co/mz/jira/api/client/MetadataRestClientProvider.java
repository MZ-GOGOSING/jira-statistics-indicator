package kr.co.mz.jira.api.client;

import com.atlassian.jira.rest.client.api.MetadataRestClient;

public interface MetadataRestClientProvider {

  MetadataRestClient get();
}
