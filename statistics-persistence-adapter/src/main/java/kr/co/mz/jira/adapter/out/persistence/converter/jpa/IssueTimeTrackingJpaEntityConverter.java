package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Optional;
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
    return Optional.ofNullable(issueTimeTrackingDomainEntity)
        .map(source -> IssueTimeTrackingJpaEntity.builder()
            .originalEstimateMinutes(source.getOriginalEstimateMinutes())
            .remainingEstimateMinutes(source.getRemainingEstimateMinutes())
            .timeSpentMinutes(source.getTimeSpentMinutes())
            .issue(issueJpaEntity)
            .build())
        .orElse(null);
  }
}
