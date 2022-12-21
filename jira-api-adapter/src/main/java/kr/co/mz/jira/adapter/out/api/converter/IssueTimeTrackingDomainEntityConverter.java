package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueTimeTrackingDomainEntityConverter implements Converter<TimeTracking, IssueTimeTrackingDomainEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueTimeTrackingDomainEntity convert(final TimeTracking timeTracking) {
    return null;
  }
}
