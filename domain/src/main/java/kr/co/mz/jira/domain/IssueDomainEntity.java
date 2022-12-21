package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDomainEntity {

  private final Long id;

  private final String key;

  private final Set<String> labels;

  private final LocalDateTime dueDate;

  private final LocalDateTime updateDate;

  private final LocalDateTime creationDate;

  private final String assigneeDisplayName;

  private final String reporterDisplayName;

  private final String summary;

  private final String issueTypeName;

  private final String statusName;

  private final IssueTimeTrackingDomainEntity timeTracking;

  private final List<IssueWorklogDomainEntity> worklogs;

  private final List<IssueChangelogGroupDomainEntity> changelog;

  private IssueDomainEntity(
      final Long id,
      final String key,
      final Set<String> labels,
      final LocalDateTime dueDate,
      final LocalDateTime updateDate,
      final LocalDateTime creationDate,
      final String assigneeDisplayName,
      final String reporterDisplayName,
      final String summary,
      final String issueTypeName,
      final String statusName,
      final IssueTimeTrackingDomainEntity timeTracking,
      final List<IssueWorklogDomainEntity> worklogs,
      final List<IssueChangelogGroupDomainEntity> changelog
  ) {
    this.id = id;
    this.key = key;
    this.labels = labels;
    this.dueDate = dueDate;
    this.updateDate = updateDate;
    this.creationDate = creationDate;
    this.assigneeDisplayName = assigneeDisplayName;
    this.reporterDisplayName = reporterDisplayName;
    this.summary = summary;
    this.issueTypeName = issueTypeName;
    this.statusName = statusName;
    this.timeTracking = timeTracking;
    this.worklogs = worklogs;
    this.changelog = changelog;
  }

  public static IssueDomainEntity withoutId(
      final String key,
      final Set<String> labels,
      final LocalDateTime dueDate,
      final LocalDateTime updateDate,
      final LocalDateTime creationDate,
      final String assigneeDisplayName,
      final String reporterDisplayName,
      final String summary,
      final String issueTypeName,
      final String statusName,
      final IssueTimeTrackingDomainEntity timeTracking,
      final List<IssueWorklogDomainEntity> worklogs,
      final List<IssueChangelogGroupDomainEntity> changelog
  ) {
    return IssueDomainEntity.builder()
        .key(key)
        .labels(labels)
        .dueDate(dueDate)
        .updateDate(updateDate)
        .creationDate(creationDate)
        .assigneeDisplayName(assigneeDisplayName)
        .reporterDisplayName(reporterDisplayName)
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
      final String key,
      final Set<String> labels,
      final LocalDateTime dueDate,
      final LocalDateTime updateDate,
      final LocalDateTime creationDate,
      final String assigneeDisplayName,
      final String reporterDisplayName,
      final String summary,
      final String issueTypeName,
      final String statusName,
      final IssueTimeTrackingDomainEntity timeTracking,
      final List<IssueWorklogDomainEntity> worklogs,
      final List<IssueChangelogGroupDomainEntity> changelog
  ) {
    return IssueDomainEntity.builder()
        .id(id)
        .key(key)
        .labels(labels)
        .dueDate(dueDate)
        .updateDate(updateDate)
        .creationDate(creationDate)
        .assigneeDisplayName(assigneeDisplayName)
        .reporterDisplayName(reporterDisplayName)
        .summary(summary)
        .issueTypeName(issueTypeName)
        .statusName(statusName)
        .timeTracking(timeTracking)
        .worklogs(worklogs)
        .changelog(changelog)
        .build();
  }
}
