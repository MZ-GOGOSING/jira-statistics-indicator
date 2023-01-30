package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDomainEntity {

    private final Long id;

    private final String epicKey;

    private final String key;

    private final String issueURI;

    private final String watchersURI;

    private final Set<String> labels;

    private final LocalDateTime dueDate;

    private final LocalDateTime updateDate;

    private final LocalDateTime creationDate;

    private final String assigneeUsername;

    private final String reporterUsername;

    private final String summary;

    private final String issueTypeName;

    private final String statusName;

    private final IssueTimeTrackingDomainEntity timeTracking;

    private final List<IssueWorklogDomainEntity> worklogs;

    private final List<IssueChangelogGroupDomainEntity> changelog;

    private IssueDomainEntity(
            final Long id,
            final String epicKey,
            final String key,
            final String issueURI,
            final String watchersURI,
            final Set<String> labels,
            final LocalDateTime dueDate,
            final LocalDateTime updateDate,
            final LocalDateTime creationDate,
            final String assigneeUsername,
            final String reporterUsername,
            final String summary,
            final String issueTypeName,
            final String statusName,
            final IssueTimeTrackingDomainEntity timeTracking,
            final List<IssueWorklogDomainEntity> worklogs,
            final List<IssueChangelogGroupDomainEntity> changelog
    ) {
        AssertHelper.hasText(key, "Issue Key 는 빈 문자열일 수 없습니다.");

        this.id = id;
        this.epicKey = epicKey;
        this.key = key;
        this.issueURI = issueURI;
        this.watchersURI = watchersURI;
        this.labels = labels;
        this.dueDate = dueDate;
        this.updateDate = updateDate;
        this.creationDate = creationDate;
        this.assigneeUsername = assigneeUsername;
        this.reporterUsername = reporterUsername;
        this.summary = summary;
        this.issueTypeName = issueTypeName;
        this.statusName = statusName;
        this.timeTracking = timeTracking;
        this.worklogs = worklogs;
        this.changelog = changelog;
    }

    public static IssueDomainEntity fromOrigin(
            final String epicKey,
            final String key,
            final String issueURI,
            final String watchersURI,
            final Set<String> labels,
            final LocalDateTime dueDate,
            final LocalDateTime updateDate,
            final LocalDateTime creationDate,
            final String assigneeUsername,
            final String reporterUsername,
            final String summary,
            final String issueTypeName,
            final String statusName,
            final IssueTimeTrackingDomainEntity timeTracking,
            final List<IssueWorklogDomainEntity> worklogs,
            final List<IssueChangelogGroupDomainEntity> changelog
    ) {
        return IssueDomainEntity.builder()
                .epicKey(epicKey)
                .key(key)
                .issueURI(issueURI)
                .watchersURI(watchersURI)
                .labels(labels)
                .dueDate(dueDate)
                .updateDate(updateDate)
                .creationDate(creationDate)
                .assigneeUsername(assigneeUsername)
                .reporterUsername(reporterUsername)
                .summary(summary)
                .issueTypeName(issueTypeName)
                .statusName(statusName)
                .timeTracking(timeTracking)
                .worklogs(worklogs)
                .changelog(changelog)
                .build();
    }

    public static IssueDomainEntity withId(
            final Long id,
            final String epicKey,
            final String key,
            final String issueURI,
            final String watchersURI,
            final Set<String> labels,
            final LocalDateTime dueDate,
            final LocalDateTime updateDate,
            final LocalDateTime creationDate,
            final String assigneeUsername,
            final String reporterUsername,
            final String summary,
            final String issueTypeName,
            final String statusName,
            final IssueTimeTrackingDomainEntity timeTracking,
            final List<IssueWorklogDomainEntity> worklogs,
            final List<IssueChangelogGroupDomainEntity> changelog
    ) {
        AssertHelper.isPositive(id, "ID 는 0 이하 일 수 없습니다.");

        return IssueDomainEntity.builder()
                .id(id)
                .epicKey(epicKey)
                .key(key)
                .issueURI(issueURI)
                .watchersURI(watchersURI)
                .labels(labels)
                .dueDate(dueDate)
                .updateDate(updateDate)
                .creationDate(creationDate)
                .assigneeUsername(assigneeUsername)
                .reporterUsername(reporterUsername)
                .summary(summary)
                .issueTypeName(issueTypeName)
                .statusName(statusName)
                .timeTracking(timeTracking)
                .worklogs(worklogs)
                .changelog(changelog)
                .build();
    }
}
