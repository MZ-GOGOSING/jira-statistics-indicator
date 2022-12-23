package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueChangelogItemDomainEntityConverter
    implements Converter<IssueChangelogItemJpaEntity, IssueChangelogItemDomainEntity> {

  @Override
  public IssueChangelogItemDomainEntity convert(
      final IssueChangelogItemJpaEntity issueChangelogItemJpaEntity
  ) {
    return IssueChangelogItemDomainEntity.withId(
        issueChangelogItemJpaEntity.getId(),
        issueChangelogItemJpaEntity.getIssueChangelogGroup().getId(),
        issueChangelogItemJpaEntity.getField(),
        issueChangelogItemJpaEntity.getFromString(),
        issueChangelogItemJpaEntity.getToString()
    );
  }
}
