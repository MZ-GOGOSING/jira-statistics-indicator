package kr.co.mz.jira.application;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import kr.co.mz.jira.application.port.in.SyncAllStatusUseCase;
import kr.co.mz.jira.application.port.in.response.SyncAllStatusInResponse;
import kr.co.mz.jira.application.port.out.CreateAllStatusPort;
import kr.co.mz.jira.application.port.out.DeleteAllStatusPort;
import kr.co.mz.jira.application.port.out.FetchAllStatusPort;
import kr.co.mz.jira.domain.StatusDomainEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SyncAllStatusService implements SyncAllStatusUseCase {

  private final FetchAllStatusPort fetchAllStatusPort;

  private final CreateAllStatusPort createAllStatusPort;

  private final DeleteAllStatusPort deleteAllStatusPort;

  @Override
  public SyncAllStatusInResponse sync() {
    final var syncDate = LocalDate.now();
    final var statusDomainEntities = this.saveAllStatuses(syncDate);

    return SyncAllStatusInResponse.of(syncDate, statusDomainEntities);
  }

  private List<StatusDomainEntity> saveAllStatuses(final LocalDate syncDate) {
    final var fetchedStatusDomainEntities = fetchAllStatusPort.fetchAll(syncDate);

    if (CollectionUtils.isNotEmpty(fetchedStatusDomainEntities)) {
      deleteAllStatusPort.deleteAllBySyncDate(syncDate);
    }

    return CollectionUtils.isEmpty(fetchedStatusDomainEntities)
        ? Collections.emptyList()
        : createAllStatusPort.saveAll(fetchedStatusDomainEntities);
  }
}
