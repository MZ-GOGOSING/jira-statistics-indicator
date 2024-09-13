package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueCommentDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueCommentJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.support.converter.BiConverter;

public class IssueCommentJpaEntityConverter
    implements BiConverter<IssueJpaEntity, IssueCommentDomainEntity, IssueCommentJpaEntity> {

  @Override
  public IssueCommentJpaEntity convert(
      final IssueJpaEntity issueJpaEntity,
      final IssueCommentDomainEntity issueCommentDomainEntity
  ) {
    if (Objects.isNull(issueCommentDomainEntity)) {
      return null;
    }

    return IssueCommentJpaEntity.builder()
        .issue(issueJpaEntity)
        .commentId(issueCommentDomainEntity.getCommentId())
        .authorDisplayName(issueCommentDomainEntity.getAuthorDisplayName())
        .authorAccountId(issueCommentDomainEntity.getAuthorAccountId())
        .updateAuthorDisplayName(issueCommentDomainEntity.getUpdateAuthorDisplayName())
        .updateAuthorAccountId(issueCommentDomainEntity.getUpdateAuthorAccountId())
        .creationDate(issueCommentDomainEntity.getCreationDate())
        .updateDate(issueCommentDomainEntity.getUpdateDate())
        .body(issueCommentDomainEntity.getBody())
        .build();
  }
}
