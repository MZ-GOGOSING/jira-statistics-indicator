package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueWorklogJpaEntity;
import kr.co.mz.jira.support.converter.BiConverter;

public class IssueWorklogJpaEntityConverter
    implements BiConverter<IssueJpaEntity, IssueWorklogDomainEntity, IssueWorklogJpaEntity> {

  @Override
  public IssueWorklogJpaEntity convert(
      final IssueJpaEntity issueJpaEntity,
      final IssueWorklogDomainEntity issueWorklogDomainEntity
  ) {
    if (Objects.isNull(issueWorklogDomainEntity)) {
      return null;
    }

    return IssueWorklogJpaEntity.builder()
        .issue(issueJpaEntity)
        .authorUsername(issueWorklogDomainEntity.getAuthorUsername())
        .updateAuthorUsername(issueWorklogDomainEntity.getUpdateAuthorUsername())
        .comment(issueWorklogDomainEntity.getComment())
        .creationDate(issueWorklogDomainEntity.getCreationDate())
        .updateDate(issueWorklogDomainEntity.getUpdateDate())
        .startDate(issueWorklogDomainEntity.getStartDate())
        .minutesSpent(issueWorklogDomainEntity.getMinutesSpent())
        .build();
  }
}
