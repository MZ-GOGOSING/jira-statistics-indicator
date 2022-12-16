package kr.co.mz.jira.api.client.impl;

import com.atlassian.jira.rest.client.api.MetadataRestClient;
import kr.co.mz.jira.api.client.JiraRestClientProvider;
import kr.co.mz.jira.api.client.MetadataRestClientProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(JiraRestClientProvider.class)
@RequiredArgsConstructor
public class DefaultMetadataRestClientProvider implements MetadataRestClientProvider {

  private final JiraRestClientProvider jiraRestClientProvider;

  @Override
  public MetadataRestClient get() {
    return jiraRestClientProvider.get().getMetadataClient();
  }
}
