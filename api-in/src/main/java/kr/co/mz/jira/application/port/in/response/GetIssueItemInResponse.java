package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import java.util.Set;
import kr.co.mz.jira.domain.IssueDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetIssueItemInResponse {

  private final String issueKey;

  private final String issueURI;

  private final String watchersURI;

  private final Set<String> labels;

  private final LocalDateTime dueDate;

  private final LocalDateTime updateDate;

  private final LocalDateTime creationDate;

  private final String assigneeUsername;

  private final String reporterUsername;

  private final String summary;

  private final String issueTypeName;

  private final String statusName;

  public static GetIssueItemInResponse of(final IssueDomainEntity issueDomainEntity) {
    return GetIssueItemInResponse.builder()
        .issueKey(issueDomainEntity.getKey())
        .issueURI(issueDomainEntity.getIssueURI())
        .watchersURI(issueDomainEntity.getWatchersURI())
        .labels(issueDomainEntity.getLabels())
        .dueDate(issueDomainEntity.getDueDate())
        .updateDate(issueDomainEntity.getUpdateDate())
        .creationDate(issueDomainEntity.getCreationDate())
        .assigneeUsername(issueDomainEntity.getAssigneeUsername())
        .reporterUsername(issueDomainEntity.getReporterUsername())
        .summary(issueDomainEntity.getSummary())
        .issueTypeName(issueDomainEntity.getIssueTypeName())
        .statusName(issueDomainEntity.getStatusName())
        .build();
  }
}
