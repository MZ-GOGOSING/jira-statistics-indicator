package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.application.port.in.response.GetSubjectDetailInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Schema(description = "특정 SUBJECT 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetSubjectDetailWebResponse {

  @Schema(description = "UUID", example = "f86c0f4b-38eb-464d-a81e-a01a6e37742f", required = true)
  private final String uuid;

  @Schema(
      description = "검색 JQL",
      example = "project = ITO AND worklogDate >= 2022-12-01 AND worklogDate <= 2022-12-31",
      required = true
  )
  private final String jql;

  @Schema(description = "생성자", example = "userId", required = true)
  private final String createdBy;

  @Schema(description = "등록일", example = "yyyy-MM-dd", required = true)
  private final LocalDateTime createdDate;

  @Schema(description = "소속 ISSUE 목록", required = true)
  private final List<GetIssueItemWebResponse> issueList;

  public static GetSubjectDetailWebResponse of(final GetSubjectDetailInResponse inResponse) {
    return GetSubjectDetailWebResponse.builder()
        .uuid(inResponse.getSubjectItemInResponse().getUuid())
        .jql(inResponse.getSubjectItemInResponse().getJql())
        .createdBy(inResponse.getSubjectItemInResponse().getCreatedBy())
        .createdDate(inResponse.getSubjectItemInResponse().getCreatedDate())
        .issueList(
            CollectionUtils.emptyIfNull(inResponse.getIssueItemInResponseList())
                .stream()
                .map(GetIssueItemWebResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
