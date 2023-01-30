package kr.co.mz.jira.adapter.out.document.converter;

import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.mz.jira.document.spreadsheet.statistics.StatisticsExcelSummaryRow;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.IssueTimeTrackingDomainEntity;
import kr.co.mz.support.converter.DefaultDateTimeConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;

public class StatisticsExcelSummaryRowConverter
    implements Converter<IssueDomainEntity, StatisticsExcelSummaryRow> {

  @SuppressWarnings("SimplifyStreamApiCallChains")
  @Override
  public StatisticsExcelSummaryRow convert(final IssueDomainEntity issueDomainEntity) {
    return StatisticsExcelSummaryRow.builder()
        .key(issueDomainEntity.getKey())
        .summary(issueDomainEntity.getSummary())
        .type(issueDomainEntity.getIssueTypeName())
        .labels(
            CollectionUtils.emptyIfNull(issueDomainEntity.getLabels())
                .stream()
                .collect(Collectors.joining(","))
        )
        .status(issueDomainEntity.getStatusName())
        .assignee(issueDomainEntity.getAssigneeUsername())
        .reporter(issueDomainEntity.getReporterUsername())
        .createdDate(issueDomainEntity.getCreationDate())
        .lastModifiedDate(issueDomainEntity.getUpdateDate())
        .dueDate(issueDomainEntity.getDueDate())
        .timeSpentDurationWords(this.getTimeSpentDurationWords(issueDomainEntity.getTimeTracking()))
        .build();
  }

  private String getTimeSpentDurationWords(final IssueTimeTrackingDomainEntity timeTracking) {
    return Optional.ofNullable(timeTracking)
        .map(IssueTimeTrackingDomainEntity::getTimeSpentMinutes)
        .map(Long::valueOf)
        .map(DefaultDateTimeConverter::convertDurationWords)
        .orElse(null);
  }
}
