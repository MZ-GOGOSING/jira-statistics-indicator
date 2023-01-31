package kr.co.mz.jira.adapter.out.persistence;

import java.time.LocalDate;
import kr.co.mz.jira.application.port.out.DeleteAllStatusPort;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.StatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional
public class DeleteAllStatusPersistenceAdapter implements DeleteAllStatusPort {

  private final StatusJpaRepository statusJpaRepository;

  @Override
  public void deleteAllBySyncDate(final LocalDate syncDate) {
    final var statusJpaEntities = statusJpaRepository.findAllBySyncDate(syncDate);

    if (CollectionUtils.isEmpty(statusJpaEntities)) {
      return;
    }

    statusJpaRepository.deleteAll(statusJpaEntities);
  }
}
