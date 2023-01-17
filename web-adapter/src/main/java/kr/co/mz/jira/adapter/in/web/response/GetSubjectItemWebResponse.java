package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import kr.co.mz.jira.application.port.in.response.GetSubjectItemInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "SUBJECT 항목 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetSubjectItemWebResponse {

  @Schema(description = "UUID", example = "f86c0f4b-38eb-464d-a81e-a01a6e37742f", required = true)
  private final String uuid;

  @Schema(
      description = "검색 JQL",
      example = "project = ITO AND worklogDate >= 2022-12-01 AND worklogDate <= 2022-12-31",
      required = true
  )
  private final String jql;

  @Schema(description = "JQL 검색결과 Issue Key", example = "[\"ISSUE-001\", \"ISSUE-002\"]", required = true)
  private final List<String> jqlResult;

  @Schema(description = "생성자", example = "userId", required = true)
  private final String createdBy;

  @Schema(description = "등록일", example = "yyyy-MM-dd", required = true)
  private final LocalDateTime createdDate;

  public static GetSubjectItemWebResponse of(final GetSubjectItemInResponse inResponse) {
    return GetSubjectItemWebResponse.builder()
        .uuid(inResponse.getUuid())
        .jql(inResponse.getJql())
        .jqlResult(inResponse.getJqlResult())
        .createdBy(inResponse.getCreatedBy())
        .createdDate(inResponse.getCreatedDate())
        .build();
  }
}
