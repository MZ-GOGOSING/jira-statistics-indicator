package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Min;
import kr.co.mz.jira.adapter.in.web.response.SyncSearchResultWebResponse;
import kr.co.mz.jira.application.port.in.SyncIssuesUseCase;
import kr.co.mz.support.dto.ApiResponse;
import kr.co.mz.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SYNC", description = "통계 원천 데이터 수집 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class SyncIssuesController {

  private final SyncIssuesUseCase syncIssuesUseCase;

  @Operation(summary = "ISSUE 검색결과 동기화", description = "특정 Subject 내 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/subject/sync/{subjectId}")
  public ApiResponse<SyncSearchResultWebResponse> syncIssues(
      final @PathVariable @Min(1L) Long subjectId
  ) {
    final var inResponse = syncIssuesUseCase.sync(subjectId);

    final var webResponse = SyncSearchResultWebResponse.of(inResponse);

    return ApiResponseGenerator.success(webResponse);
  }
}
