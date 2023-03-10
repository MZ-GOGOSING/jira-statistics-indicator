package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.mz.jira.adapter.in.web.response.SyncAllStatusWebResponse;
import kr.co.mz.jira.application.port.in.SyncAllStatusUseCase;
import kr.co.mz.support.dto.ApiResponse;
import kr.co.mz.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Tag(name = "SYNC", description = "통계 원천 데이터 수집 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class SyncAllStatusController {

  private final SyncAllStatusUseCase syncAllStatusUseCase;

  @Operation(summary = "Status 동기화", description = "현재 JIRA 시스템 상에 존재하는 모든 Status 목록을 DB 에 동기화 합니다.")
  @PostMapping("/status/sync")
  public ApiResponse<SyncAllStatusWebResponse> syncAllStatus() {
    final var inResponse = syncAllStatusUseCase.sync(LocalDate.now());
    final var webResponse = SyncAllStatusWebResponse.of(inResponse);

    return ApiResponseGenerator.success(webResponse);
  }
}
