package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import kr.co.mz.jira.application.port.in.response.GetIssueItemInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "특정 ISSUE 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetIssueItemWebResponse {

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

  public static GetIssueItemWebResponse of(final GetIssueItemInResponse inResponse) {
    return GetIssueItemWebResponse.builder()
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
        .build();
  }
}
