package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import java.util.Objects;
import kr.co.mz.jira.domain.StatusCategoryDomainEntity;
import kr.co.mz.jira.jpa.entity.StatusCategoryJpaEntity;
import kr.co.mz.jira.jpa.entity.StatusJpaEntity;
import kr.co.mz.support.converter.BiConverter;

public class StatusCategoryJpaEntityConverter
    implements BiConverter<StatusJpaEntity, StatusCategoryDomainEntity, StatusCategoryJpaEntity> {

  @Override
  public StatusCategoryJpaEntity convert(
      final StatusJpaEntity statusJpaEntity,
      final StatusCategoryDomainEntity statusCategoryDomainEntity
  ) {
    if (Objects.isNull(statusCategoryDomainEntity)) {
      return null;
    }

    return StatusCategoryJpaEntity.builder()
        .categoryURI(statusCategoryDomainEntity.getCategoryURI())
        .categoryId(statusCategoryDomainEntity.getCategoryId())
        .categoryKey(statusCategoryDomainEntity.getCategoryKey())
        .categoryName(statusCategoryDomainEntity.getCategoryName())
        .categoryColorName(statusCategoryDomainEntity.getCategoryColorName())
        .status(statusJpaEntity)
        .build();
  }
}
