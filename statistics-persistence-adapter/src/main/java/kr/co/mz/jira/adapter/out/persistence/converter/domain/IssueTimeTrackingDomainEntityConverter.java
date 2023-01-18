package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Objects;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueTimeTrackingJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueTimeTrackingDomainEntityConverter
    implements Converter<IssueTimeTrackingJpaEntity, IssueTimeTrackingDomainEntity> {

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public IssueTimeTrackingDomainEntity convert(
      final IssueTimeTrackingJpaEntity issueTimeTrackingJpaEntity
  ) {
    if (Objects.isNull(issueTimeTrackingJpaEntity)) {
      return null;
    }

    return IssueTimeTrackingDomainEntity.withId(
        issueTimeTrackingJpaEntity.getId(),
        issueTimeTrackingJpaEntity.getOriginalEstimateMinutes(),
        issueTimeTrackingJpaEntity.getRemainingEstimateMinutes(),
        issueTimeTrackingJpaEntity.getTimeSpentMinutes()
    );
  }
}
