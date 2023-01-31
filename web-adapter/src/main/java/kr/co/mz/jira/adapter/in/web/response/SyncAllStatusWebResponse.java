package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import kr.co.mz.jira.application.port.in.response.SyncAllStatusInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Schema(description = "Status 동기화 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SyncAllStatusWebResponse {

  @Schema(description = "동기화 수행 날짜", example = "2023-01-01")
  private final LocalDate syncDate;

  @Schema(description = "동기화에 성공한 Status 목록", example = "[\"TO DO\", \"IN PROGRESS\"]")
  private final List<String> syncedStatusNameList;

  public static SyncAllStatusWebResponse of(final SyncAllStatusInResponse inResponse) {
    return SyncAllStatusWebResponse.builder()
        .syncDate(inResponse.getSyncDate())
        .syncedStatusNameList(inResponse.getSyncedStatusNameList())
        .build();
  }
}
