package kr.co.mz.jira.adapter.out.persistence.converter;

import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueJpaEntityConverter implements Converter<IssueDomainEntity, IssueJpaEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueJpaEntity convert(final IssueDomainEntity issueDomainEntity) {
    return null;
  }
}
