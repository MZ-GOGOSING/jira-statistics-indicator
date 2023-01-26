package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import kr.co.mz.support.converter.BiConverter;

public class IssueChangelogItemJpaEntityConverter
    implements BiConverter<IssueChangelogGroupJpaEntity, IssueChangelogItemDomainEntity, IssueChangelogItemJpaEntity> {

  @Override
  public IssueChangelogItemJpaEntity convert(
      final IssueChangelogGroupJpaEntity issueChangelogGroupJpaEntity,
      final IssueChangelogItemDomainEntity issueChangelogItemDomainEntity
  ) {
    if (Objects.isNull(issueChangelogItemDomainEntity)) {
      return null;
    }

    return IssueChangelogItemJpaEntity.builder()
        .issueChangelogGroup(issueChangelogGroupJpaEntity)
        .field(issueChangelogItemDomainEntity.getField())
        .fromString(issueChangelogItemDomainEntity.getFromString())
        .toString(issueChangelogItemDomainEntity.getToString())
        .build();
  }
}
