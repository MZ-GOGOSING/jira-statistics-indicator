package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.AddressableNamedEntity;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Issue;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.support.converter.LocalDateTimeConverter;
import kr.co.mz.support.converter.StreamConverter;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<Issue, IssueDomainEntity> {

  private static final IssueTimeTrackingDomainEntityConverter ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER =
      new IssueTimeTrackingDomainEntityConverter();

  private static final IssueWorklogDomainEntityConverter ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER =
      new IssueWorklogDomainEntityConverter();

  private static final IssueChangelogGroupDomainEntityConverter ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER =
      new IssueChangelogGroupDomainEntityConverter();

  private static final IssueCommentDomainEntityConverter ISSUE_COMMENT_DOMAIN_ENTITY_CONVERTER =
      new IssueCommentDomainEntityConverter();

  @Override
  public IssueDomainEntity convert(final Issue issue) {
    return IssueDomainEntity.fromOrigin(
        issue.getKey(),
        issue.getSelf().toString(),
        Optional.ofNullable(issue.getWatchers())
            .map(BasicWatchers::getSelf)
            .map(URI::toString)
            .orElse(null),
        issue.getLabels(),
        LocalDateTimeConverter.fromJoda(issue.getDueDate()),
        LocalDateTimeConverter.fromJoda(issue.getUpdateDate()),
        LocalDateTimeConverter.fromJoda(issue.getCreationDate()),
        Optional.ofNullable(issue.getAssignee())
            .map(AddressableNamedEntity::getName)
            .orElse(null),
        Optional.ofNullable(issue.getReporter())
            .map(AddressableNamedEntity::getName)
            .orElse(null),
        issue.getSummary(),
        issue.getDescription(),
        issue.getIssueType().getName(),
        issue.getStatus().getName(),
        Optional.ofNullable(issue.getTimeTracking())
            .map(ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER::convert)
            .orElse(null),
        StreamConverter
            .fromIterable(issue.getWorklogs())
            .map(ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER::convert)
            .collect(Collectors.toList()),
        StreamConverter
            .fromIterable(issue.getChangelog())
            .map(ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER::convert)
            .collect(Collectors.toList()),
        StreamConverter
            .fromIterable(issue.getComments())
            .map(ISSUE_COMMENT_DOMAIN_ENTITY_CONVERTER::convert)
            .collect(Collectors.toList())
    );
  }
}
