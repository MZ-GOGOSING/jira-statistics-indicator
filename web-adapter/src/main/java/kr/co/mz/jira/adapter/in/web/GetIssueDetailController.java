package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.Min;
import kr.co.mz.jira.adapter.in.web.response.GetIssueDetailWebResponse;
import kr.co.mz.jira.application.port.in.GetIssueDetailQuery;
import kr.co.mz.jira.support.dto.ApiResponse;
import kr.co.mz.jira.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SEARCH", description = "통계 원천 데이터 조회 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class GetIssueDetailController {

  private final GetIssueDetailQuery getIssueDetailQuery;

  @Operation(summary = "특정 ISSUE 조회", description = "특정 ISSUE 를 조회할 수 있습니다.")
  @GetMapping("/issue/{id}")
  public ApiResponse<GetIssueDetailWebResponse> getIssueDetail(
      @Parameter(description = "ISSUE ID")
      final @PathVariable @Min(1L) Long id
  ) {
    final var inResponse = getIssueDetailQuery.loadById(id);

    final var webResponse = GetIssueDetailWebResponse.of(inResponse);

    return ApiResponseGenerator.success(webResponse);
  }
}
