package kr.co.mz.jira.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.persistence.converter.domain.StatusDomainEntityConverter;
import kr.co.mz.jira.adapter.out.persistence.converter.jpa.StatusJpaEntityConverter;
import kr.co.mz.jira.application.port.out.CreateAllStatusPort;
import kr.co.mz.jira.domain.StatusDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.entity.StatusJpaEntity;
import kr.co.mz.jira.jpa.repository.StatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional
public class CreateAllStatusPersistenceAdapter implements CreateAllStatusPort {

  private static final StatusJpaEntityConverter STATUS_JPA_ENTITY_CONVERTER =
      new StatusJpaEntityConverter();

  private static final StatusDomainEntityConverter STATUS_DOMAIN_ENTITY_CONVERTER =
      new StatusDomainEntityConverter();

  private final StatusJpaRepository statusJpaRepository;

  @Override
  public List<StatusDomainEntity> saveAll(final List<StatusDomainEntity> statusDomainEntities) {
    final var statusJpaEntities = statusDomainEntities
        .stream()
        .map(this::saveStatusJpaEntity)
        .collect(Collectors.toList());

    return statusJpaEntities
        .stream()
        .map(STATUS_DOMAIN_ENTITY_CONVERTER::convert)
        .collect(Collectors.toList());
  }

  private StatusJpaEntity saveStatusJpaEntity(final StatusDomainEntity statusDomainEntity) {
    final var statusJpaEntity = STATUS_JPA_ENTITY_CONVERTER.convert(
        statusDomainEntity
    );
    return statusJpaRepository.save(statusJpaEntity);
  }
}
