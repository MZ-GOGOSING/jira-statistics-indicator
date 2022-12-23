package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Optional;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import kr.co.mz.jira.support.converter.BiConverter;

public class IssueChangelogItemJpaEntityConverter
    implements BiConverter<IssueChangelogGroupJpaEntity, IssueChangelogItemDomainEntity, IssueChangelogItemJpaEntity> {

  @Override
  public IssueChangelogItemJpaEntity convert(
      final IssueChangelogGroupJpaEntity issueChangelogGroupJpaEntity,
      final IssueChangelogItemDomainEntity issueChangelogItemDomainEntity
  ) {
    return Optional.ofNullable(issueChangelogItemDomainEntity)
        .map(source -> IssueChangelogItemJpaEntity.builder()
            .issueChangelogGroup(issueChangelogGroupJpaEntity)
            .field(source.getField())
            .fromString(source.getFromString())
            .toString(source.getToString())
            .build())
        .orElse(null);
  }
}
