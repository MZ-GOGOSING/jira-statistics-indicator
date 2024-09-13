package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueCommentDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueCommentJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueCommentDomainEntityConverter
    implements Converter<IssueCommentJpaEntity, IssueCommentDomainEntity> {

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public IssueCommentDomainEntity convert(final IssueCommentJpaEntity issueCommentJpaEntity) {
    if (Objects.isNull(issueCommentJpaEntity)) {
      return null;
    }

    return IssueCommentDomainEntity.withId(
        issueCommentJpaEntity.getId(),
        issueCommentJpaEntity.getIssue().getId(),
        issueCommentJpaEntity.getCommentId(),
        issueCommentJpaEntity.getAuthorDisplayName(),
        issueCommentJpaEntity.getAuthorAccountId(),
        issueCommentJpaEntity.getUpdateAuthorDisplayName(),
        issueCommentJpaEntity.getUpdateAuthorAccountId(),
        issueCommentJpaEntity.getCreationDate(),
        issueCommentJpaEntity.getUpdateDate(),
        issueCommentJpaEntity.getBody()
    );
  }
}
