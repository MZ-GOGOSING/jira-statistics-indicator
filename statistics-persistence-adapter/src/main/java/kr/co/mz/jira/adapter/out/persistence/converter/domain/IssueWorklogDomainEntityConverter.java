package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Optional;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueWorklogJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueWorklogDomainEntityConverter
    implements Converter<IssueWorklogJpaEntity, IssueWorklogDomainEntity> {

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public IssueWorklogDomainEntity convert(final IssueWorklogJpaEntity issueWorklogJpaEntity) {
    return Optional.ofNullable(issueWorklogJpaEntity)
        .map(source -> IssueWorklogDomainEntity.withId(
            source.getId(),
            source.getIssue().getId(),
            source.getAuthorUsername(),
            source.getUpdateAuthorUsername(),
            source.getComment(),
            source.getCreationDate(),
            source.getUpdateDate(),
            source.getStartDate(),
            source.getMinutesSpent()
        ))
        .orElse(null);
  }
}
