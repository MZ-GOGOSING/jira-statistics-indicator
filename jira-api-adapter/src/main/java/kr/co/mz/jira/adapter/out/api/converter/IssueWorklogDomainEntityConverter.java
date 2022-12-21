package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueWorklogDomainEntityConverter implements Converter<Worklog, IssueWorklogDomainEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueWorklogDomainEntity convert(final Worklog worklog) {
    return null;
  }
}
