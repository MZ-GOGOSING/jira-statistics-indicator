package kr.co.mz.jira.adapter.out.api;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import kr.co.mz.jira.adapter.out.api.converter.IssueDomainEntityConverter;
import kr.co.mz.jira.api.service.IssueRestClientService;
import kr.co.mz.jira.api.service.WorklogRestClientService;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.support.converter.StreamConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class FetchIssueAdapter implements FetchAllIssuePort {

  private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
      new IssueDomainEntityConverter();

  private final IssueRestClientService issueRestClientService;

  private final WorklogRestClientService worklogRestClientService;

  @Override
  public List<IssueDomainEntity> fetchAllByIssueKeyList(final List<String> issueKeyList) {
    final List<Issue> issueList = issueRestClientService.loadAllByIssueKeyList(issueKeyList);
    final Map<String, List<Worklog>> issueKeyAndWorklogListMap = this.fetchAllByIssueList(issueList);

    return CollectionUtils.emptyIfNull(issueList)
        .stream()
        .map(issue -> {
          final var issueKey = issue.getKey();
          final var worklogs = issueKeyAndWorklogListMap.getOrDefault(issueKey, Collections.emptyList());
          return ISSUE_DOMAIN_ENTITY_CONVERTER.convert(issue, worklogs);
        })
        .collect(Collectors.toList());
  }

  private Map<String, List<Worklog>> fetchAllByIssueList(final List<Issue> issueList) {
    final var issueKeyList = issueList
        .stream()
        .filter(issue -> this.getWorklogCount(issue) == 20L)
        .map(BasicIssue::getKey)
        .collect(Collectors.toList());

    return worklogRestClientService.loadAllByIssueKeyList(issueKeyList);
  }

  private long getWorklogCount(final Issue issue) {
    return StreamConverter.fromIterable(issue.getWorklogs()).count();
  }
}
