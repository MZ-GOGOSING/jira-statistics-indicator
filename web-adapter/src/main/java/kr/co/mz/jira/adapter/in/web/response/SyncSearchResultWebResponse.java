package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "JQL 검색 및 결과 동기화 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SyncSearchResultWebResponse {

  @Schema(
      description = "동기화 결과 UUID",
      example = "123e4567-e89b-12d3-a456-556642440000",
      required = true
  )
  private final String uuid;

  @Schema(
      description = "동기화에 사용된 JQL",
      example = "project = ITO AND worklogDate >= 2022-12-01 AND worklogDate <= 2022-12-31",
      required = true
  )
  private final String jql;

  @Schema(description = "동기화에 성공한 Issue Key 목록", example = "[\"ITO-1\", \"ITO-2\"]")
  private final List<String> syncedIssueKeyList;

  @Schema(description = "동기화에 사용된 계정", example = "gogosing")
  private final String createdBy;

  public static SyncSearchResultWebResponse of(final SyncSearchResultInResponse inResponse) {
    return SyncSearchResultWebResponse.builder()
        .uuid(inResponse.getUuid())
        .jql(inResponse.getJql())
        .syncedIssueKeyList(inResponse.getSyncedIssueKeyList())
        .createdBy(inResponse.getCreatedBy())
        .build();
  }
}
