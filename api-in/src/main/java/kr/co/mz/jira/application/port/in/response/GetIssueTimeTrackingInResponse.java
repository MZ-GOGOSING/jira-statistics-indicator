package kr.co.mz.jira.application.port.in.response;

import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetIssueTimeTrackingInResponse {

  private final Integer originalEstimateMinutes;

  private final Integer remainingEstimateMinutes;

  private final Integer timeSpentMinutes;

  public static GetIssueTimeTrackingInResponse of(final IssueTimeTrackingDomainEntity issueTimeTrackingDomainEntity) {
    return GetIssueTimeTrackingInResponse.builder()
        .originalEstimateMinutes(issueTimeTrackingDomainEntity.getOriginalEstimateMinutes())
        .remainingEstimateMinutes(issueTimeTrackingDomainEntity.getRemainingEstimateMinutes())
        .timeSpentMinutes(issueTimeTrackingDomainEntity.getTimeSpentMinutes())
        .build();
  }
}
