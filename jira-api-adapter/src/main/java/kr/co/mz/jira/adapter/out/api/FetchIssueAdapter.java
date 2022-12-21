package kr.co.mz.jira.adapter.out.api;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.api.converter.IssueDomainEntityConverter;
import kr.co.mz.jira.api.service.IssueRestClientService;
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

  private final IssueRestClientService issueRestClientService;

  @Override
  public List<IssueDomainEntity> fetchAllByIssueKeyList(final List<String> issueKeyList) {
    final var issueList = issueRestClientService.loadAllByIssueKeyList(issueKeyList);
    final var issueDomainEntityConverter = new IssueDomainEntityConverter();

    return CollectionUtils.emptyIfNull(issueList)
        .stream()
        .map(issueDomainEntityConverter::convert)
        .collect(Collectors.toList());
  }
}
