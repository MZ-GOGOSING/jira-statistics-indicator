package kr.co.mz.jira.api.custom;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.util.concurrent.Promise;

public interface WorklogRestClient {

  Promise<Iterable<Worklog>> getWorklog(final String issueKey);
}
