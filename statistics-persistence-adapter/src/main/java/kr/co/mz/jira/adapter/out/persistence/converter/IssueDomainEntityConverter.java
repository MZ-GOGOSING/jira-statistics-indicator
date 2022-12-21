package kr.co.mz.jira.adapter.out.persistence.converter;

import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueDomainEntityConverter implements Converter<IssueJpaEntity, IssueDomainEntity> {

  @NonNull
  @Override
  public IssueDomainEntity convert(final @NotNull IssueJpaEntity issueJpaEntity) {
    return null;
  }
}
