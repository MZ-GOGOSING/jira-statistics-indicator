package kr.co.mz.jira.api.custom;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.util.concurrent.Promise;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

public class AsynchronousWorklogRestClient extends AbstractAsynchronousRestClient implements WorklogRestClient {

  private final URI baseUri;

  private final CustomWorklogJsonParser worklogParser = new CustomWorklogJsonParser();

  public AsynchronousWorklogRestClient(
      final URI serverUri,
      final HttpClient httpClient
  ) {
    super(httpClient);
    this.baseUri = UriBuilder.fromUri(serverUri).path("/rest/api/latest").build();
  }

  /**
   * e.g. https://jira.woowa.in/rest/api/2/issue/ITO-208/worklog/
   * */
  @Override
  public Promise<Iterable<Worklog>> getWorklog(final String issueKey) {
    final UriBuilder uriBuilder = UriBuilder.fromUri(baseUri)
        .path("issue")
        .path(issueKey)
        .path("worklog");

    return getAndParse(uriBuilder.build(), worklogParser);
  }
}
