package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import java.util.Objects;
import kr.co.mz.jira.domain.StatusCategoryDomainEntity;
import kr.co.mz.jira.jpa.entity.StatusCategoryJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class StatusCategoryDomainEntityConverter implements Converter<StatusCategoryJpaEntity, StatusCategoryDomainEntity> {

  @Override
  @SuppressWarnings({"NullableProblems", "ConstantConditions"})
  public StatusCategoryDomainEntity convert(final StatusCategoryJpaEntity statusCategoryJpaEntity) {
    if (Objects.isNull(statusCategoryJpaEntity)) {
      return null;
    }

    return StatusCategoryDomainEntity.withId(
        statusCategoryJpaEntity.getId(),
        statusCategoryJpaEntity.getCategoryURI(),
        statusCategoryJpaEntity.getCategoryId(),
        statusCategoryJpaEntity.getCategoryKey(),
        statusCategoryJpaEntity.getCategoryName(),
        statusCategoryJpaEntity.getCategoryColorName()
    );
  }
}
