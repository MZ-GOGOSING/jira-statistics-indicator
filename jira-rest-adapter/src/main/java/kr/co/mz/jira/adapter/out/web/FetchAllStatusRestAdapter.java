package kr.co.mz.jira.adapter.out.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.web.converter.StatusDomainEntityConverter;
import kr.co.mz.jira.application.port.out.FetchAllStatusPort;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.rest.client.MetadataRestClientComponent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class FetchAllStatusRestAdapter implements FetchAllStatusPort {

  private static final StatusDomainEntityConverter STATUS_DOMAIN_ENTITY_CONVERTER =
      new StatusDomainEntityConverter();

  private final MetadataRestClientComponent metadataRestClientComponent;

  @Override
  public List<StatusDomainEntity> fetchAll(final LocalDate syncDate) {
    final var statusList = metadataRestClientComponent.loadAllStatuses();

    return CollectionUtils.emptyIfNull(statusList)
        .stream()
        .map(status -> STATUS_DOMAIN_ENTITY_CONVERTER.convert(syncDate, status))
        .collect(Collectors.toList());
  }
}
