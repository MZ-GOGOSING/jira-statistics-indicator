package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.application.port.in.response.GetIssueDetailInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Schema(description = "특정 ISSUE 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetIssueDetailWebResponse {

  @Schema(description = "id", example = "1", required = true)
  private final Long id;

  @Schema(description = "Jira Issue Key", example = "ITO-80", required = true)
  private final String issueKey;

  @Schema(description = "Issue REST URI", required = true)
  private final String issueURI;

  @Schema(description = "Watchers REST URI", required = true)
  private final String watchersURI;

  @Schema(description = "Issue Labels. (Comma Delimiter)")
  private final Set<String> labels;

  @Schema(description = "목표일")
  private final LocalDateTime dueDate;

  @Schema(description = "최근 수정일")
  private final LocalDateTime updateDate;

  @Schema(description = "생성일", required = true)
  private final LocalDateTime creationDate;

  @Schema(description = "Assignee 계정")
  private final String assigneeUsername;

  @Schema(description = "Reporter 계정")
  private final String reporterUsername;

  @Schema(description = "제목", required = true)
  private final String summary;

  @Schema(description = "Issue 유형")
  private final String issueTypeName;

  @Schema(description = "Issue 상태", required = true)
  private final String statusName;

  @Schema(description = "Time Tracking")
  private final GetIssueTimeTrackingWebResponse timeTracking;

  @Schema(description = "Work Log")
  private final List<IssueWorklogWebResponse> worklogs;

  @Schema(description = "Change Log Groups")
  private final List<IssueChangelogGroupWebResponse> changelog;

  public static GetIssueDetailWebResponse of(final GetIssueDetailInResponse inResponse) {
    return GetIssueDetailWebResponse.builder()
        .id(inResponse.getId())
        .issueKey(inResponse.getIssueKey())
        .issueURI(inResponse.getIssueURI())
        .watchersURI(inResponse.getWatchersURI())
        .labels(inResponse.getLabels())
        .dueDate(inResponse.getDueDate())
        .updateDate(inResponse.getUpdateDate())
        .creationDate(inResponse.getCreationDate())
        .assigneeUsername(inResponse.getAssigneeUsername())
        .reporterUsername(inResponse.getReporterUsername())
        .summary(inResponse.getSummary())
        .issueTypeName(inResponse.getIssueTypeName())
        .statusName(inResponse.getStatusName())
        .timeTracking(
            Optional.ofNullable(inResponse.getTimeTracking())
                .map(GetIssueTimeTrackingWebResponse::of)
                .orElse(null)
        )
        .worklogs(
            CollectionUtils.emptyIfNull(inResponse.getWorklogs())
                .stream()
                .map(IssueWorklogWebResponse::of)
                .collect(Collectors.toList())
        )
        .changelog(
            CollectionUtils.emptyIfNull(inResponse.getChangelog())
                .stream()
                .map(IssueChangelogGroupWebResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
