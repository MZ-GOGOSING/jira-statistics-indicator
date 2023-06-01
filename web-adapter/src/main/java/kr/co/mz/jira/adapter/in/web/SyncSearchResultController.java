package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kr.co.mz.jira.adapter.in.web.request.SyncSearchResultWebCommand;
import kr.co.mz.jira.adapter.in.web.request.SyncSearchWorkLogWebCommand;
import kr.co.mz.jira.adapter.in.web.response.SyncSearchResultWebResponse;
import kr.co.mz.jira.application.port.in.SyncSearchResultUseCase;
import kr.co.mz.jira.support.converter.DefaultDateTimeConverter;
import kr.co.mz.jira.support.dto.ApiResponse;
import kr.co.mz.jira.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

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
  public ApiResponse<String> searchSyncWorkLog(
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

  //project = ITO and worklogAuthor = mz_kspark and worklogDate >= '2023-05-25' and worklogDate <= '2023-05-31'
  @Operation(summary = "지난 일주일 로그 확인(목요일기준)", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping("/week-work-log")
  public ApiResponse<String> searchWeekWorkLog(
          final @RequestBody @Valid SyncSearchWorkLogWebCommand webCommand
  ) {
    LocalDate nowDate = LocalDate.now();
    String startDate = DefaultDateTimeConverter.convertDate(
            nowDate.minusDays(changeMinusDays()));
    String endDate = DefaultDateTimeConverter.convertDate(nowDate);
    if(nowDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
      endDate = DefaultDateTimeConverter.convertDate(nowDate.minusDays(1));
    }

    var inCommand = "project = ITO and worklogAuthor = " + webCommand.getWorker()
            + " and worklogDate >= '" + startDate + "'" + " and worklogDate <= '" + endDate + "'";

    final var inResponse = syncSearchResultUseCase.sync(inCommand);

    syncSearchResultUseCase.deleteIssueWorkerLog(webCommand.getWorker(), startDate, endDate);
    // sync
    syncSearchResultUseCase.syncIssueLog(inResponse.getUuid());

    return ApiResponseGenerator.success(
            syncSearchResultUseCase.selectWorkerLog(webCommand.getWorker(), startDate, endDate)
    );
  }

  private int changeMinusDays() {
    int days = LocalDate.now().getDayOfWeek().getValue();
    return  days > 4? days-4:days+3;
  }
}
