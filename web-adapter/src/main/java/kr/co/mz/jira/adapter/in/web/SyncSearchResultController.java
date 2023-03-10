package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kr.co.mz.jira.adapter.in.web.request.command.SyncSearchResultWebCommand;
import kr.co.mz.jira.adapter.in.web.response.SyncSearchResultWebResponse;
import kr.co.mz.jira.application.port.in.SyncSearchResultUseCase;
import kr.co.mz.support.dto.ApiResponse;
import kr.co.mz.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SYNC", description = "통계 원천 데이터 수집 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class SyncSearchResultController {

  private final SyncSearchResultUseCase syncSearchResultUseCase;

  @Operation(summary = "검색결과 동기화", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/subject/sync")
  public ApiResponse<SyncSearchResultWebResponse> syncSearchResult(
      final @RequestBody @Valid SyncSearchResultWebCommand webCommand
  ) {
    final var inCommand = webCommand.getJql();
    final var inResponse = syncSearchResultUseCase.sync(inCommand);

    final var webResponse = SyncSearchResultWebResponse.of(inResponse);

    return ApiResponseGenerator.success(webResponse);
  }
}
