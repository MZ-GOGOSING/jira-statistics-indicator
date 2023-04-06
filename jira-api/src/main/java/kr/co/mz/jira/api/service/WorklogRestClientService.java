package kr.co.mz.jira.api.service;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.util.concurrent.Promise;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import kr.co.mz.jira.api.custom.WorklogRestClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class WorklogRestClientService {

  private final WorklogRestClient worklogRestClient;

  @SuppressWarnings("UnstableApiUsage")
  public Map<String, List<Worklog>> loadAllByIssueKeyList(final List<String> issueKeyList) {
    final var fetchedIssueKeyAndWorklogListMap = CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(issueKey -> Map.entry(issueKey, worklogRestClient.getWorklog(issueKey)))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    return CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .map(issueKey -> Map.entry(issueKey, this.proceed(fetchedIssueKeyAndWorklogListMap.get(issueKey))))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private List<Worklog> proceed(final Promise<Iterable<Worklog>> iterablePromise) {
    final var worklogIterable = iterablePromise.claim();

    return StreamSupport
        .stream(worklogIterable.spliterator(), false)
        .collect(Collectors.toList());
  }
}
