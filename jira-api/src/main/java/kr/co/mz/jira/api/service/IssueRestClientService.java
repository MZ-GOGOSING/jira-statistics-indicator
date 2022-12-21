package kr.co.mz.jira.api.service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.util.concurrent.Promise;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class IssueRestClientService {

  private final IssueRestClient issueRestClient;

  @SuppressWarnings("UnstableApiUsage")
  public List<Issue> loadAllByIssueKeyList(final List<String> issueKeyList) {
    return CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(issueRestClient::getIssue)
        .map(Promise::claim)
        .collect(Collectors.toList());
  }
}
