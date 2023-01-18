package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.mz.jira.application.port.in.response.GetIssueTimeTrackingInResponse;
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
public class GetIssueTimeTrackingWebResponse {

  @Schema(description = "Estimated (minute)", example = "10")
  private final Integer originalEstimateMinutes;

  @Schema(description = "Remaining (minute)", example = "10")
  private final Integer remainingEstimateMinutes;

  @Schema(description = "Logged (minute)", example = "10")
  private final Integer timeSpentMinutes;

  public static GetIssueTimeTrackingWebResponse of(final GetIssueTimeTrackingInResponse inResponse) {
    return GetIssueTimeTrackingWebResponse.builder()
        .originalEstimateMinutes(inResponse.getOriginalEstimateMinutes())
        .remainingEstimateMinutes(inResponse.getRemainingEstimateMinutes())
        .timeSpentMinutes(inResponse.getTimeSpentMinutes())
        .build();
  }
}
