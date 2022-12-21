package kr.co.mz.jira.domain;

import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TimeTrackingDomainEntity {

  private final Long id;

  private final Long issueId;

  private final Integer originalEstimateMinutes;

  private final Integer remainingEstimateMinutes;

  private final Integer timeSpentMinutes;

  private TimeTrackingDomainEntity(
      final Long id,
      final Long issueId,
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");
    AssertHelper.isTrue(
        ObjectUtils.allNotNull(originalEstimateMinutes, remainingEstimateMinutes, timeSpentMinutes),
        "Issue 의 TimeTracking 데이터 필드는 모두 null 일 수 없습니다."
    );

    this.id = id;
    this.issueId = issueId;
    this.originalEstimateMinutes = originalEstimateMinutes;
    this.remainingEstimateMinutes = remainingEstimateMinutes;
    this.timeSpentMinutes = timeSpentMinutes;
  }

  public static TimeTrackingDomainEntity withoutId(
      final Long issueId,
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    return TimeTrackingDomainEntity.builder()
        .issueId(issueId)
        .originalEstimateMinutes(originalEstimateMinutes)
        .remainingEstimateMinutes(remainingEstimateMinutes)
        .timeSpentMinutes(timeSpentMinutes)
        .build();
  }

  public static TimeTrackingDomainEntity withId(
      final Long id,
      final Long issueId,
      final Integer originalEstimateMinutes,
      final Integer remainingEstimateMinutes,
      final Integer timeSpentMinutes
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");

    return TimeTrackingDomainEntity.builder()
        .id(id)
        .issueId(issueId)
        .originalEstimateMinutes(originalEstimateMinutes)
        .remainingEstimateMinutes(remainingEstimateMinutes)
        .timeSpentMinutes(timeSpentMinutes)
        .build();
  }
}
