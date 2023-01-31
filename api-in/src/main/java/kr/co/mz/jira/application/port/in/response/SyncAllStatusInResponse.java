package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.StatusDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SyncAllStatusInResponse {

  private final LocalDate syncDate;

  private final List<String> syncedStatusDescriptionList;

  public static SyncAllStatusInResponse of(
      final LocalDate syncDate,
      final List<StatusDomainEntity> statusDomainEntities
  ) {
    return SyncAllStatusInResponse.builder()
        .syncDate(syncDate)
        .syncedStatusDescriptionList(
            CollectionUtils.emptyIfNull(statusDomainEntities)
                .stream()
                .map(StatusDomainEntity::getDescription)
                .sorted()
                .collect(Collectors.toList())
        )
        .build();
  }
}
