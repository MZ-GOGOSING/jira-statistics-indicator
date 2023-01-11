package kr.co.mz.jira.api.service;

import static com.atlassian.jira.rest.client.api.IssueRestClient.Expandos.CHANGELOG;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.IssueRestClient.Expandos;
import com.atlassian.jira.rest.client.api.domain.Issue;
import io.atlassian.util.concurrent.Promise;
import java.util.List;
import java.util.Set;
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

  /**
   * @see IssueRestClient#getIssue(String, Iterable)
   */
  private static final Set<Expandos> ISSUE_ADDITIONAL_EXPANDS_SET = Set.of(CHANGELOG);

  private final IssueRestClient issueRestClient;

  public List<Issue> loadAllByIssueKeyList(final List<String> issueKeyList) {
    return CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(issueKey -> issueRestClient.getIssue(issueKey, ISSUE_ADDITIONAL_EXPANDS_SET))
        .map(Promise::claim)
        .collect(Collectors.toList());
  }
}
