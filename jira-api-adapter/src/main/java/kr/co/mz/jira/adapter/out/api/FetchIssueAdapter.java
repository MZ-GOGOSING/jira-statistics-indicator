package kr.co.mz.jira.adapter.out.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.api.converter.IssueDomainEntityConverter;
import kr.co.mz.jira.api.service.IssueRestClientService;
import kr.co.mz.jira.api.service.WorklogRestClientService;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.domain.IssueDomainEntity;
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
    final var issueList = issueRestClientService.loadAllByIssueKeyList(issueKeyList);
    final var issueKeyAndWorklogListMap = worklogRestClientService.loadAllByIssueKeyList(issueKeyList);

    return CollectionUtils.emptyIfNull(issueList)
        .stream()
        .map(issue -> {
          final var issueKey = issue.getKey();
          final var worklogs = issueKeyAndWorklogListMap.getOrDefault(issueKey, Collections.emptyList());
          return ISSUE_DOMAIN_ENTITY_CONVERTER.convert(issue, worklogs);
        })
        .collect(Collectors.toList());
  }

}
