package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.support.converter.BiConverter;
import org.apache.commons.collections4.CollectionUtils;

public class IssueChangelogGroupJpaEntityConverter
    implements BiConverter<IssueJpaEntity, IssueChangelogGroupDomainEntity, IssueChangelogGroupJpaEntity> {

  private static final IssueChangelogItemJpaEntityConverter ISSUE_CHANGELOG_ITEM_JPA_ENTITY_CONVERTER =
      new IssueChangelogItemJpaEntityConverter();

  @Override
  public IssueChangelogGroupJpaEntity convert(
      final IssueJpaEntity issueJpaEntity,
      final IssueChangelogGroupDomainEntity issueChangelogGroupDomainEntity
  ) {
    if (Objects.isNull(issueChangelogGroupDomainEntity)) {
      return null;
    }

    final var issueChangelogGroupJpaEntity = IssueChangelogGroupJpaEntity.builder()
        .issue(issueJpaEntity)
        .authorUsername(issueChangelogGroupDomainEntity.getAuthorUsername())
        .created(issueChangelogGroupDomainEntity.getCreated())
        .build();

    final var issueChangelogItemJpaEntities = this.buildIssueChangelogItemJpaEntities(issueChangelogGroupJpaEntity, issueChangelogGroupDomainEntity.getItems());

    issueChangelogGroupJpaEntity.getItems().addAll(issueChangelogItemJpaEntities);

    return issueChangelogGroupJpaEntity;
  }

  private Set<IssueChangelogItemJpaEntity> buildIssueChangelogItemJpaEntities(
      final IssueChangelogGroupJpaEntity issueChangelogGroupJpaEntity,
      final List<IssueChangelogItemDomainEntity> issueChangelogItemDomainEntities
  ) {
    return CollectionUtils.emptyIfNull(issueChangelogItemDomainEntities)
        .stream()
        .map(issueChangelogItemDomainEntity -> ISSUE_CHANGELOG_ITEM_JPA_ENTITY_CONVERTER.convert(
            issueChangelogGroupJpaEntity,
            issueChangelogItemDomainEntity
        ))
        .filter(Objects::nonNull)
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
