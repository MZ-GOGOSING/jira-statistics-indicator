package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "Issue 검색 및 동기화 결과 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CreateSearchResultWebResponse {

  @Schema(description = "동기화 결과 UUID", example = "123e4567-e89b-12d3-a456-556642440000", required = true)
  private final String uuid;

  @Schema(description = "동기화에 사용된 JQL", example = "project = ITO", required = true)
  private final String jql;

  @Schema(description = "동기화 결과 생성 시간", example = "\"yyyy-MM-dd\"", required = true)
  private final LocalDateTime createdDate;

  public static CreateSearchResultWebResponse of() {
    return CreateSearchResultWebResponse.builder()
        .build();
  }
}
