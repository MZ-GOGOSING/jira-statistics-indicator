package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import kr.co.mz.jira.domain.IssueCommentDomainEntity;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueCommentJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueTimeTrackingJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueWorklogJpaEntity;
import kr.co.mz.support.converter.BiConverter;
import org.apache.commons.collections4.CollectionUtils;

public class IssueJpaEntityConverter implements BiConverter<Long, IssueDomainEntity, IssueJpaEntity> {

  private static final IssueTimeTrackingJpaEntityConverter ISSUE_TIME_TRACKING_JPA_ENTITY_CONVERTER =
      new IssueTimeTrackingJpaEntityConverter();

  private static final IssueWorklogJpaEntityConverter ISSUE_WORKLOG_JPA_ENTITY_CONVERTER =
      new IssueWorklogJpaEntityConverter();

  private static final IssueChangelogGroupJpaEntityConverter ISSUE_CHANGELOG_GROUP_JPA_ENTITY_CONVERTER =
      new IssueChangelogGroupJpaEntityConverter();

  private static final IssueCommentJpaEntityConverter ISSUE_COMMENT_JPA_ENTITY_CONVERTER =
      new IssueCommentJpaEntityConverter();

  @Override
  public IssueJpaEntity convert(
      final Long subjectId,
      final IssueDomainEntity issueDomainEntity
  ) {
    final var issueJpaEntity = IssueJpaEntity.builder()
        .subjectId(subjectId)
        .issueKey(issueDomainEntity.getKey())
        .issueURI(issueDomainEntity.getIssueURI())
        .watchersURI(issueDomainEntity.getWatchersURI())
        .labels(issueDomainEntity.getLabels())
        .dueDate(issueDomainEntity.getDueDate())
        .updateDate(issueDomainEntity.getUpdateDate())
        .creationDate(issueDomainEntity.getCreationDate())
        .assigneeUsername(issueDomainEntity.getAssigneeUsername())
        .reporterUsername(issueDomainEntity.getReporterUsername())
        .summary(issueDomainEntity.getSummary())
        .description(issueDomainEntity.getDescription())
        .issueTypeName(issueDomainEntity.getIssueTypeName())
        .statusName(issueDomainEntity.getStatusName())
        .build();

    final var issueTimeTrackingJpaEntity = this.buildIssueTimeTrackingJpaEntity(issueJpaEntity, issueDomainEntity.getTimeTracking());
    final var issueWorklogJpaEntities = this.buildIssueWorklogJpaEntities(issueJpaEntity, issueDomainEntity.getWorklogs());
    final var issueChangelogGroupJpaEntities = this.buildIssueChangelogGroupJpaEntities(issueJpaEntity, issueDomainEntity.getChangelog());
    final var issueCommentJpaEntities = this.buildIssueCommentJpaEntities(issueJpaEntity, issueDomainEntity.getComments());

    issueJpaEntity.setTimeTracking(issueTimeTrackingJpaEntity);
    issueJpaEntity.getWorklogs().addAll(issueWorklogJpaEntities);
    issueJpaEntity.getChangelog().addAll(issueChangelogGroupJpaEntities);
    issueJpaEntity.getComments().addAll(issueCommentJpaEntities);

    return issueJpaEntity;
  }

  private IssueTimeTrackingJpaEntity buildIssueTimeTrackingJpaEntity(
      final IssueJpaEntity issueJpaEntity,
      final IssueTimeTrackingDomainEntity issueTimeTrackingDomainEntity
  ) {
    return ISSUE_TIME_TRACKING_JPA_ENTITY_CONVERTER
        .convert(issueJpaEntity, issueTimeTrackingDomainEntity);
  }

  private Set<IssueWorklogJpaEntity> buildIssueWorklogJpaEntities(
      final IssueJpaEntity issueJpaEntity,
      final List<IssueWorklogDomainEntity> issueWorklogDomainEntities
  ) {
    return CollectionUtils.emptyIfNull(issueWorklogDomainEntities)
        .stream()
        .map(issueWorklogDomainEntity -> ISSUE_WORKLOG_JPA_ENTITY_CONVERTER.convert(
            issueJpaEntity,
            issueWorklogDomainEntity
        ))
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  private Set<IssueChangelogGroupJpaEntity> buildIssueChangelogGroupJpaEntities(
      final IssueJpaEntity issueJpaEntity,
      final List<IssueChangelogGroupDomainEntity> issueChangelogGroupDomainEntities
  ) {
    return CollectionUtils.emptyIfNull(issueChangelogGroupDomainEntities)
        .stream()
        .map(issueChangelogGroupDomainEntity -> ISSUE_CHANGELOG_GROUP_JPA_ENTITY_CONVERTER.convert(
            issueJpaEntity,
            issueChangelogGroupDomainEntity
        ))
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  private Set<IssueCommentJpaEntity> buildIssueCommentJpaEntities(
      final IssueJpaEntity issueJpaEntity,
      final List<IssueCommentDomainEntity> issueCommentDomainEntities
  ) {
    return CollectionUtils.emptyIfNull(issueCommentDomainEntities)
        .stream()
        .map(issueCommentDomainEntity -> ISSUE_COMMENT_JPA_ENTITY_CONVERTER.convert(
            issueJpaEntity,
            issueCommentDomainEntity
        ))
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
