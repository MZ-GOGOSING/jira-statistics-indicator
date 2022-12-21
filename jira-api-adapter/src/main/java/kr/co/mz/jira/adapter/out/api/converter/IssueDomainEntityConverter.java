package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.Issue;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueDomainEntityConverter implements Converter<Issue, IssueDomainEntity> {

  @NonNull
  @Override
  public IssueDomainEntity convert(final @NotNull Issue issue) {
    return null;
  }
}
