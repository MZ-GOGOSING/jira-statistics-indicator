package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDomainEntity {

    private  Long id;

    private String epicKey;

    private  String key;

    private  String issueURI;

    private  String watchersURI;

    private  Set<String> labels;

    private  LocalDateTime dueDate;

    private  LocalDateTime updateDate;

    private  LocalDateTime creationDate;

    private  String assigneeUsername;

    private  String reporterUsername;

    private  String summary;

    private  String issueTypeName;

    private  String statusName;

    private  String sprint;

    private  String parentTask;

    private  boolean isSubtask;

    private  IssueTimeTrackingDomainEntity timeTracking;

    private  List<IssueWorklogDomainEntity> worklogs;

    private  List<IssueChangelogGroupDomainEntity> changelog;

    private IssueDomainEntity(
             Long id,
             String epicKey,
             String key,
             String issueURI,
             String watchersURI,
             Set<String> labels,
             LocalDateTime dueDate,
             LocalDateTime updateDate,
             LocalDateTime creationDate,
             String assigneeUsername,
             String reporterUsername,
             String summary,
             String issueTypeName,
             String statusName,
             String sprint,
             String parentTask,
             boolean isSubtask,
             IssueTimeTrackingDomainEntity timeTracking,
             List<IssueWorklogDomainEntity> worklogs,
             List<IssueChangelogGroupDomainEntity> changelog
    ) {
        this.parentTask = parentTask;
        this.isSubtask = isSubtask;

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
        this.sprint = sprint;
        this.timeTracking = timeTracking;
        this.worklogs = worklogs;
        this.changelog = changelog;
    }

    public static IssueDomainEntity fromOrigin(
             String epicKey,
             String key,
             String issueURI,
             String watchersURI,
             Set<String> labels,
             LocalDateTime dueDate,
             LocalDateTime updateDate,
             LocalDateTime creationDate,
             String assigneeUsername,
             String reporterUsername,
             String summary,
             String issueTypeName,
             String statusName,
             String sprint,
             String parentTask,
             boolean isSubtask,
             IssueTimeTrackingDomainEntity timeTracking,
             List<IssueWorklogDomainEntity> worklogs,
             List<IssueChangelogGroupDomainEntity> changelog
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
                .sprint(sprint)
                .parentTask(parentTask)
                .isSubtask(isSubtask)
                .timeTracking(timeTracking)
                .worklogs(worklogs)
                .changelog(changelog)
                .build();
    }

    public static IssueDomainEntity withId(
             Long id,
             String epicKey,
             String key,
             String issueURI,
             String watchersURI,
             Set<String> labels,
             LocalDateTime dueDate,
             LocalDateTime updateDate,
             LocalDateTime creationDate,
             String assigneeUsername,
             String reporterUsername,
             String summary,
             String issueTypeName,
             String statusName,
             String sprint,
             String parentTask,
             boolean isSubtask,
             IssueTimeTrackingDomainEntity timeTracking,
             List<IssueWorklogDomainEntity> worklogs,
             List<IssueChangelogGroupDomainEntity> changelog
    ) {

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
                .sprint(sprint)
                .parentTask(parentTask)
                .isSubtask(isSubtask)
                .timeTracking(timeTracking)
                .worklogs(worklogs)
                .changelog(changelog)
                .build();
    }

}
