package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.Issue;
import kr.co.mz.jira.domain.IssueDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<Issue, IssueDomainEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueDomainEntity convert(final Issue issue) {
    return null;
  }
}
