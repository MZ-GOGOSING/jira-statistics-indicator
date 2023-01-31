package kr.co.mz.jira.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.persistence.converter.domain.StatusDomainEntityConverter;
import kr.co.mz.jira.application.port.out.LoadAllStatusPort;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.StatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoadAllStatusPersistenceAdapter implements LoadAllStatusPort {

  private static final StatusDomainEntityConverter STATUS_DOMAIN_ENTITY_CONVERTER =
      new StatusDomainEntityConverter();

  private final StatusJpaRepository statusJpaRepository;

  @Override
  public List<StatusDomainEntity> findAllBySyncDate(final LocalDate syncDate) {
    final var statusJpaEntities = statusJpaRepository.findAllBySyncDate(syncDate);

    return CollectionUtils.emptyIfNull(statusJpaEntities)
        .stream()
        .map(STATUS_DOMAIN_ENTITY_CONVERTER::convert)
        .collect(Collectors.toList());
  }
}
