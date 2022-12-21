package kr.co.mz.jira.adapter.out.persistence.converter;

import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueJpaEntityConverter implements Converter<IssueDomainEntity, IssueJpaEntity> {

  @NonNull
  @Override
  public IssueJpaEntity convert(final @NotNull IssueDomainEntity issueDomainEntity) {
    return null;
  }
}
