package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueTimeTrackingJpaEntity;
import kr.co.mz.jira.support.converter.BiConverter;

public class IssueTimeTrackingJpaEntityConverter
    implements BiConverter<IssueJpaEntity, IssueTimeTrackingDomainEntity, IssueTimeTrackingJpaEntity> {

  @Override
  public IssueTimeTrackingJpaEntity convert(
      final IssueJpaEntity issueJpaEntity,
      final IssueTimeTrackingDomainEntity issueTimeTrackingDomainEntity
  ) {
    if (Objects.isNull(issueTimeTrackingDomainEntity)) {
      return null;
    }

    return IssueTimeTrackingJpaEntity.builder()
        .originalEstimateMinutes(issueTimeTrackingDomainEntity.getOriginalEstimateMinutes())
        .remainingEstimateMinutes(issueTimeTrackingDomainEntity.getRemainingEstimateMinutes())
        .timeSpentMinutes(issueTimeTrackingDomainEntity.getTimeSpentMinutes())
        .issue(issueJpaEntity)
        .build();
  }
}
