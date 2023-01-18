package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.mz.jira.application.port.in.response.IssueChangelogItemInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "ISSUE CHANGE LOG (History) 항목 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogItemWebResponse {

  @Schema(description = "필드명", example = "status")
  private final String field;

  @Schema(description = "~ 으로 부터", example = "In Progress")
  private final String fromString;

  @Schema(description = "~ 으로", example = "In Review")
  private final String toString;

  public static IssueChangelogItemWebResponse of(
      final IssueChangelogItemInResponse inResponse
  ) {
    return IssueChangelogItemWebResponse.builder()
        .field(inResponse.getField())
        .fromString(inResponse.getFromString())
        .toString(inResponse.getToString())
        .build();
  }
}
