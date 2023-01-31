package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import kr.co.mz.jira.domain.StatusCategoryDomainEntity;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.jpa.entity.StatusCategoryJpaEntity;
import kr.co.mz.jira.jpa.entity.StatusJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class StatusDomainEntityConverter implements Converter<StatusJpaEntity, StatusDomainEntity> {

  private static final StatusCategoryDomainEntityConverter STATUS_CATEGORY_DOMAIN_ENTITY_CONVERTER =
      new StatusCategoryDomainEntityConverter();

  @Override
  public StatusDomainEntity convert(final StatusJpaEntity statusJpaEntity) {
    final var statusCategoryDomainEntity = this.buildStatusCategoryDomainEntity(statusJpaEntity.getStatusCategory());

    return StatusDomainEntity.withId(
        statusJpaEntity.getId(),
        statusJpaEntity.getSyncDate(),
        statusJpaEntity.getStatusName(),
        statusJpaEntity.getStatusId(),
        statusJpaEntity.getStatusURI(),
        statusJpaEntity.getDescription(),
        statusJpaEntity.getIconUrl(),
        statusCategoryDomainEntity
    );
  }

  private StatusCategoryDomainEntity buildStatusCategoryDomainEntity(
      final StatusCategoryJpaEntity statusCategoryJpaEntity
  ) {
    return STATUS_CATEGORY_DOMAIN_ENTITY_CONVERTER.convert(statusCategoryJpaEntity);
  }
}
