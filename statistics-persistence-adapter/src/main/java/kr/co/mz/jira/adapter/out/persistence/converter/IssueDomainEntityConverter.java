package kr.co.mz.jira.adapter.out.persistence.converter;

import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<IssueJpaEntity, IssueDomainEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueDomainEntity convert(final IssueJpaEntity issueJpaEntity) {
    return null;
  }
}
