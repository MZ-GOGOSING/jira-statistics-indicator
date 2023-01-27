package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

public class IssueChangelogGroupDomainEntityConverter
    implements Converter<IssueChangelogGroupJpaEntity, IssueChangelogGroupDomainEntity> {

  private static final IssueChangelogItemDomainEntityConverter ISSUE_CHANGELOG_ITEM_DOMAIN_ENTITY_CONVERTER =
      new IssueChangelogItemDomainEntityConverter();

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public IssueChangelogGroupDomainEntity convert(
      final IssueChangelogGroupJpaEntity issueChangelogGroupJpaEntity
  ) {
    if (Objects.isNull(issueChangelogGroupJpaEntity)) {
      return null;
    }

    final var issueChangelogItemDomainEntities = this.buildIssueChangelogItemDomainEntities(issueChangelogGroupJpaEntity.getItems());

    return IssueChangelogGroupDomainEntity.withId(
        issueChangelogGroupJpaEntity.getId(),
        issueChangelogGroupJpaEntity.getIssue().getId(),
        issueChangelogGroupJpaEntity.getAuthorUsername(),
        issueChangelogGroupJpaEntity.getCreated(),
        issueChangelogItemDomainEntities
    );
  }

  private List<IssueChangelogItemDomainEntity> buildIssueChangelogItemDomainEntities(
      final Set<IssueChangelogItemJpaEntity> issueChangelogItemJpaEntities
  ) {
    return CollectionUtils.emptyIfNull(issueChangelogItemJpaEntities)
        .stream()
        .map(ISSUE_CHANGELOG_ITEM_DOMAIN_ENTITY_CONVERTER::convert)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingLong(IssueChangelogItemDomainEntity::getId))
        .collect(Collectors.toList());
  }
}
