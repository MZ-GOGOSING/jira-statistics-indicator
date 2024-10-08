package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Comparator;
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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<IssueJpaEntity, IssueDomainEntity> {

  private static final IssueTimeTrackingDomainEntityConverter ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER =
      new IssueTimeTrackingDomainEntityConverter();

  private static final IssueWorklogDomainEntityConverter ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER =
      new IssueWorklogDomainEntityConverter();

  private static final IssueChangelogGroupDomainEntityConverter ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER =
      new IssueChangelogGroupDomainEntityConverter();

  private static final IssueCommentDomainEntityConverter ISSUE_COMMENT_DOMAIN_ENTITY_CONVERTER =
      new IssueCommentDomainEntityConverter();

  @Override
  public IssueDomainEntity convert(final IssueJpaEntity issueJpaEntity) {
    final var issueTimeTrackingDomainEntity = this.buildIssueTimeTrackingDomainEntity(issueJpaEntity.getTimeTracking());
    final var issueWorklogDomainEntities = this.buildIssueWorklogDomainEntities(issueJpaEntity.getWorklogs());
    final var issueChangelogGroupDomainEntities = this.buildIssueChangelogGroupDomainEntities(issueJpaEntity.getChangelog());
    final var issueCommentDomainEntities = this.buildIssueCommentDomainEntities(issueJpaEntity.getComments());

    return IssueDomainEntity.withId(
        issueJpaEntity.getId(),
        issueJpaEntity.getIssueKey(),
        issueJpaEntity.getIssueURI(),
        issueJpaEntity.getWatchersURI(),
        issueJpaEntity.getLabels(),
        issueJpaEntity.getDueDate(),
        issueJpaEntity.getUpdateDate(),
        issueJpaEntity.getCreationDate(),
        issueJpaEntity.getAssigneeUsername(),
        issueJpaEntity.getReporterUsername(),
        issueJpaEntity.getSummary(),
        issueJpaEntity.getDescription(),
        issueJpaEntity.getIssueTypeName(),
        issueJpaEntity.getStatusName(),
        issueTimeTrackingDomainEntity,
        issueWorklogDomainEntities,
        issueChangelogGroupDomainEntities,
        issueCommentDomainEntities
    );
  }

  private IssueTimeTrackingDomainEntity buildIssueTimeTrackingDomainEntity(
      final IssueTimeTrackingJpaEntity issueTimeTrackingJpaEntity
  ) {
    return ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER.convert(issueTimeTrackingJpaEntity);
  }

  private List<IssueWorklogDomainEntity> buildIssueWorklogDomainEntities(
      final Set<IssueWorklogJpaEntity> issueWorklogJpaEntities
  ) {
    return CollectionUtils.emptyIfNull(issueWorklogJpaEntities)
        .stream()
        .map(ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER::convert)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingLong(IssueWorklogDomainEntity::getId))
        .collect(Collectors.toList());
  }

  private List<IssueChangelogGroupDomainEntity> buildIssueChangelogGroupDomainEntities(
      final Set<IssueChangelogGroupJpaEntity> issueChangelogGroupJpaEntities
  ) {
    return CollectionUtils.emptyIfNull(issueChangelogGroupJpaEntities)
        .stream()
        .map(ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER::convert)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingLong(IssueChangelogGroupDomainEntity::getId))
        .collect(Collectors.toList());
  }

  private List<IssueCommentDomainEntity> buildIssueCommentDomainEntities(
      final Set<IssueCommentJpaEntity> issueCommentJpaEntities
  ) {
    return CollectionUtils.emptyIfNull(issueCommentJpaEntities)
        .stream()
        .map(ISSUE_COMMENT_DOMAIN_ENTITY_CONVERTER::convert)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingLong(IssueCommentDomainEntity::getId))
        .collect(Collectors.toList());
  }
}
