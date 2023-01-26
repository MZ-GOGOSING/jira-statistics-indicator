package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueTimeTrackingDomainEntityConverter implements Converter<TimeTracking, IssueTimeTrackingDomainEntity> {

  @Override
  public IssueTimeTrackingDomainEntity convert(final TimeTracking timeTracking) {
    return IssueTimeTrackingDomainEntity.fromOrigin(
        timeTracking.getOriginalEstimateMinutes(),
        timeTracking.getRemainingEstimateMinutes(),
        timeTracking.getTimeSpentMinutes()
    );
  }
}
