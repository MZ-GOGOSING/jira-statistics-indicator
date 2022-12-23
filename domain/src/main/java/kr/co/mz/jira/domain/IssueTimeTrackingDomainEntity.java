package kr.co.mz.jira.domain;

import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueTimeTrackingDomainEntity {

  private final Long issueId;

  private final Integer originalEstimateMinutes;

  private final Integer remainingEstimateMinutes;

  private final Integer timeSpentMinutes;

  public static IssueTimeTrackingDomainEntity fromOrigin(
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    return IssueTimeTrackingDomainEntity.builder()
        .originalEstimateMinutes(originalEstimateMinutes)
        .remainingEstimateMinutes(remainingEstimateMinutes)
        .timeSpentMinutes(timeSpentMinutes)
        .build();
  }

  public static IssueTimeTrackingDomainEntity withoutId(
      final Long issueId,
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    return IssueTimeTrackingDomainEntity.builder()
        .issueId(issueId)
        .originalEstimateMinutes(originalEstimateMinutes)
        .remainingEstimateMinutes(remainingEstimateMinutes)
        .timeSpentMinutes(timeSpentMinutes)
        .build();
  }

  public static IssueTimeTrackingDomainEntity withId(
      final Long issueId,
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    return IssueTimeTrackingDomainEntity.builder()
        .issueId(issueId)
        .originalEstimateMinutes(originalEstimateMinutes)
        .remainingEstimateMinutes(remainingEstimateMinutes)
        .timeSpentMinutes(timeSpentMinutes)
        .build();
  }
}
