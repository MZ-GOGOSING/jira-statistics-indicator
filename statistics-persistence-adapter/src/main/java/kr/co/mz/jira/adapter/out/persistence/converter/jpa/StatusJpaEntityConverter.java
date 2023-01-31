package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import kr.co.mz.jira.domain.StatusCategoryDomainEntity;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.jpa.entity.StatusCategoryJpaEntity;
import kr.co.mz.jira.jpa.entity.StatusJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class StatusJpaEntityConverter implements Converter<StatusDomainEntity, StatusJpaEntity> {

  private static final StatusCategoryJpaEntityConverter STATUS_CATEGORY_JPA_ENTITY_CONVERTER =
      new StatusCategoryJpaEntityConverter();

  @Override
  public StatusJpaEntity convert(final StatusDomainEntity statusDomainEntity) {
    final var statusJpaEntity = StatusJpaEntity.builder()
        .syncDate(statusDomainEntity.getSyncDate())
        .statusId(statusDomainEntity.getStatusId())
        .description(statusDomainEntity.getDescription())
        .iconUrl(statusDomainEntity.getIconUrl())
        .build();

    final var statusCategoryJpaEntity = this.buildStatusCategoryJpaEntity(statusJpaEntity, statusDomainEntity.getStatusCategory());

    statusJpaEntity.setStatusCategory(statusCategoryJpaEntity);

    return statusJpaEntity;
  }

  private StatusCategoryJpaEntity buildStatusCategoryJpaEntity(
      final StatusJpaEntity statusJpaEntity,
      final StatusCategoryDomainEntity statusCategoryDomainEntity
  ) {
    return STATUS_CATEGORY_JPA_ENTITY_CONVERTER
        .convert(statusJpaEntity, statusCategoryDomainEntity);
  }
}
