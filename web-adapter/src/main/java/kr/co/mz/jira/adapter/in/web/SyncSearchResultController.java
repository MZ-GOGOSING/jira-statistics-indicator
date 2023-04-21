package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kr.co.mz.jira.adapter.in.web.request.SyncSearchResultWebCommand;
import kr.co.mz.jira.adapter.in.web.request.SyncSearchWeekWorkLogWebCommand;
import kr.co.mz.jira.adapter.in.web.request.SyncSearchWorkLogWebCommand;
import kr.co.mz.jira.adapter.in.web.response.SyncSearchResultWebResponse;
import kr.co.mz.jira.application.port.in.SyncSearchResultUseCase;
import kr.co.mz.jira.support.dto.ApiResponse;
import kr.co.mz.jira.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "STATISTICS", description = "통계 관리 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class SyncSearchResultController {

  private final SyncSearchResultUseCase syncSearchResultUseCase;

  @Operation(summary = "검색결과 동기화", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/sync")
  public ApiResponse<SyncSearchResultWebResponse> syncSearchResult(
      final @RequestBody @Valid SyncSearchResultWebCommand webCommand
  ) {
    final var inCommand = webCommand.getJql();
    final var inResponse = syncSearchResultUseCase.sync(inCommand);

    final var webResponse = SyncSearchResultWebResponse.of(inResponse);

    // sync
    syncSearchResultUseCase.syncIssueLog(inResponse.getUuid());

    return ApiResponseGenerator.success(webResponse);
  }

  //project = ITO and worklogAuthor = mz_kspark and worklogDate = '2023-03-23'
  @Operation(summary = "오늘 로그 확인", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/sync-work-log")
  public ApiResponse<String> syncSearchWorkLog(
          final @RequestBody @Valid SyncSearchWorkLogWebCommand webCommand
  ) {
    var inCommand = "project = ITO and worklogAuthor = " + webCommand.getWorker()
            + " and worklogDate = '" + webCommand.getWorkDate() + "'";
    final var inResponse = syncSearchResultUseCase.sync(inCommand);

    syncSearchResultUseCase.deleteIssueWorkerLog(webCommand.getWorker(), webCommand.getWorkDate());
    // sync
    syncSearchResultUseCase.syncIssueLog(inResponse.getUuid());

    return ApiResponseGenerator.success(
            syncSearchResultUseCase.selectWorkerLog(webCommand.getWorker(), webCommand.getWorkDate())
    );
  }

  @Operation(summary = "지정일자 로그 확인", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/sync-work-log-week")
  public ApiResponse<String> syncSearchWorkLogForSearchDay(
      final @RequestBody @Valid SyncSearchWeekWorkLogWebCommand webCommand
  ) {
    var inCommand = "project = ITO and worklogAuthor = " + webCommand.getWorker()
        + " and worklogDate >= " + webCommand.getStrDate() + " AND worklogDate <= " + webCommand.getEndDate();
//    worklogDate >= 2023-04-13 AND worklogDate <= 2023-04-19
    final var inResponse = syncSearchResultUseCase.sync(inCommand);

    syncSearchResultUseCase.deleteIssueWorkerLogForBoundary(webCommand.getWorker(), webCommand.getStrDate(), webCommand.getEndDate());
    // sync
    syncSearchResultUseCase.syncIssueLog(inResponse.getUuid());

    return ApiResponseGenerator.success(
        syncSearchResultUseCase.selectWorkerLogForBoundary(webCommand.getWorker(), webCommand.getStrDate(), webCommand.getEndDate())
    );
  }
}
