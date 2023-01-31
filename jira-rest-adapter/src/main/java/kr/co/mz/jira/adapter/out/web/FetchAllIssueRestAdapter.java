package kr.co.mz.jira.adapter.out.web;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.web.converter.IssueDomainEntityConverter;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.rest.client.IssueRestClientComponent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class FetchAllIssueRestAdapter implements FetchAllIssuePort {

  private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
      new IssueDomainEntityConverter();

  private final IssueRestClientComponent issueRestClientComponent;

  @Override
  public List<IssueDomainEntity> fetchAllByIssueKeyList(final List<String> issueKeyList) {
    final var issueList = issueRestClientComponent.loadAllByIssueKeyList(issueKeyList);

    return CollectionUtils.emptyIfNull(issueList)
        .stream()
        .map(ISSUE_DOMAIN_ENTITY_CONVERTER::convert)
        .collect(Collectors.toList());
  }
}
