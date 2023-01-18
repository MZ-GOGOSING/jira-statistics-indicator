package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import kr.co.mz.jira.application.port.in.response.IssueWorklogInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "ISSUE TIME TRACKING 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueWorklogWebResponse {

  @Schema(description = "작성자 계정", example = "mz_gogosing")
  private final String authorUsername;

  @Schema(description = "수정자 계정", example = "mz_gogosing")
  private final String updateAuthorUsername;

  @Schema(description = "댓글 내용", example = "가나다라마바사")
  private final String comment;

  @Schema(description = "생성일", example = "2022-12-08 16:32:18.000000")
  private final LocalDateTime creationDate;

  @Schema(description = "최종 수정일", example = "2022-12-08 16:32:18.000000")
  private final LocalDateTime updateDate;

  @Schema(description = "시작일", example = "2022-12-08 16:32:18.000000")
  private final LocalDateTime startDate;

  @Schema(description = "작업 시간 (minute)", example = "160")
  private final Integer minutesSpent;

  public static IssueWorklogWebResponse of(final IssueWorklogInResponse inResponse) {
    return IssueWorklogWebResponse.builder()
        .authorUsername(inResponse.getAuthorUsername())
        .updateAuthorUsername(inResponse.getUpdateAuthorUsername())
        .comment(inResponse.getComment())
        .creationDate(inResponse.getCreationDate())
        .updateDate(inResponse.getUpdateDate())
        .startDate(inResponse.getStartDate())
        .minutesSpent(inResponse.getMinutesSpent())
        .build();
  }
}
