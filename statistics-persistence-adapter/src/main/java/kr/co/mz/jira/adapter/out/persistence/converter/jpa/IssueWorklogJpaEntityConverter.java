package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Optional;
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
    return Optional.ofNullable(issueWorklogDomainEntity)
        .map(source -> IssueWorklogJpaEntity.builder()
            .issue(issueJpaEntity)
            .authorUsername(source.getAuthorUsername())
            .updateAuthorUsername(source.getUpdateAuthorUsername())
            .comment(source.getComment())
            .creationDate(source.getCreationDate())
            .updateDate(source.getUpdateDate())
            .startDate(source.getStartDate())
            .minutesSpent(source.getMinutesSpent())
            .build())
        .orElse(null);
  }
}
