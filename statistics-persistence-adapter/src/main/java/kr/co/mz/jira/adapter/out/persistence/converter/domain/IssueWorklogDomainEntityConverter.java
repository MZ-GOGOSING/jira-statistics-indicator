package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueWorklogJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueWorklogDomainEntityConverter
    implements Converter<IssueWorklogJpaEntity, IssueWorklogDomainEntity> {

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public IssueWorklogDomainEntity convert(final IssueWorklogJpaEntity issueWorklogJpaEntity) {
    if (Objects.isNull(issueWorklogJpaEntity)) {
      return null;
    }

    return IssueWorklogDomainEntity.withId(
        issueWorklogJpaEntity.getId(),
        issueWorklogJpaEntity.getIssue().getId(),
        issueWorklogJpaEntity.getAuthorUsername(),
        issueWorklogJpaEntity.getUpdateAuthorUsername(),
        issueWorklogJpaEntity.getComment(),
        issueWorklogJpaEntity.getCreationDate(),
        issueWorklogJpaEntity.getUpdateDate(),
        issueWorklogJpaEntity.getStartDate(),
        issueWorklogJpaEntity.getMinutesSpent()
    );
  }
}
