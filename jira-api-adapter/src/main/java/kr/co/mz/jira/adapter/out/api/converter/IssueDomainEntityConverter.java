package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.AddressableNamedEntity;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.support.converter.StreamConverter;
import kr.co.mz.jira.support.converter.LocalDateTimeConverter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<Issue, IssueDomainEntity> {

    private static final IssueTimeTrackingDomainEntityConverter ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER =
            new IssueTimeTrackingDomainEntityConverter();

    private static final IssueWorklogDomainEntityConverter ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER =
            new IssueWorklogDomainEntityConverter();

    private static final IssueChangelogGroupDomainEntityConverter ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER =
            new IssueChangelogGroupDomainEntityConverter();

    @Override
    public IssueDomainEntity convert(final Issue issue) {
        // IssueField{id=customfield_10006, name=Epic Link, type=null, value=ITO-1}
        IssueField issueField = issue.getField("customfield_10006"); // Epic Link
        String epicKey = ObjectUtils.defaultIfNull(issueField.getValue(), "").toString();
        return IssueDomainEntity.fromOrigin(
                epicKey,
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
                        .collect(Collectors.toList())
        );
    }
}
