package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.Status;
import java.time.LocalDate;
import java.util.Optional;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.support.converter.BiConverter;

public class StatusDomainEntityConverter implements
    BiConverter<LocalDate, Status, StatusDomainEntity> {

  private static final StatusCategoryDomainEntityConverter STATUS_CATEGORY_DOMAIN_ENTITY_CONVERTER =
      new StatusCategoryDomainEntityConverter();

  @Override
  public StatusDomainEntity convert(final LocalDate syncDate, final Status status) {
    return StatusDomainEntity.fromOrigin(
        syncDate,
        status.getName(),
        status.getId(),
        status.getSelf().toString(),
        status.getDescription(),
        status.getIconUrl().toString(),
        Optional.ofNullable(status.getStatusCategory())
            .map(STATUS_CATEGORY_DOMAIN_ENTITY_CONVERTER::convert)
            .orElse(null)
    );
  }
}
