package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Optional;
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
    return Optional.ofNullable(issueTimeTrackingJpaEntity)
        .map(source -> IssueTimeTrackingDomainEntity.withId(
            source.getId(),
            source.getOriginalEstimateMinutes(),
            source.getRemainingEstimateMinutes(),
            source.getTimeSpentMinutes()
        ))
        .orElse(null);
  }
}
