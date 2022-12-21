package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueTimeTrackingDomainEntityConverter implements Converter<TimeTracking, IssueTimeTrackingDomainEntity> {

  @NonNull
  @Override
  public IssueTimeTrackingDomainEntity convert(final @NotNull TimeTracking timeTracking) {
    return null;
  }
}
