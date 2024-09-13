package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueDomainEntity;
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
public class GetIssueDetailInResponse {

  private final Long id;

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

  private final String description;

  private final String issueTypeName;

  private final String statusName;

  private final GetIssueTimeTrackingInResponse timeTracking;

  private final List<IssueWorklogInResponse> worklogs;

  private final List<IssueChangelogGroupInResponse> changelog;

  private final List<IssueCommentInResponse> comments;

  public static GetIssueDetailInResponse of(final IssueDomainEntity issueDomainEntity) {
    return GetIssueDetailInResponse.builder()
        .id(issueDomainEntity.getId())
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
        .description(issueDomainEntity.getDescription())
        .issueTypeName(issueDomainEntity.getIssueTypeName())
        .statusName(issueDomainEntity.getStatusName())
        .timeTracking(
            Optional.ofNullable(issueDomainEntity.getTimeTracking())
                .map(GetIssueTimeTrackingInResponse::of)
                .orElse(null)
        )
        .worklogs(
            CollectionUtils.emptyIfNull(issueDomainEntity.getWorklogs())
                .stream()
                .map(IssueWorklogInResponse::of)
                .collect(Collectors.toList())
        )
        .changelog(
            CollectionUtils.emptyIfNull(issueDomainEntity.getChangelog())
                .stream()
                .map(IssueChangelogGroupInResponse::of)
                .collect(Collectors.toList())
        )
        .comments(
            CollectionUtils.emptyIfNull(issueDomainEntity.getComments())
                .stream()
                .map(IssueCommentInResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
