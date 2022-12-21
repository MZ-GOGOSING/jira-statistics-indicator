package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueWorklogDomainEntityConverter implements Converter<Worklog, IssueWorklogDomainEntity> {

  @NonNull
  @Override
  public IssueWorklogDomainEntity convert(final @NotNull Worklog worklog) {
    return null;
  }
}
