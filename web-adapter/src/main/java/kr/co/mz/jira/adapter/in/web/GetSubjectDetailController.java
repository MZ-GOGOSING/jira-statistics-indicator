package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.adapter.in.web.response.GetSubjectDetailWebResponse;
import kr.co.mz.jira.application.port.in.GetSubjectDetailQuery;
import kr.co.mz.jira.support.dto.ApiResponse;
import kr.co.mz.jira.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "STATISTICS", description = "통계 관리 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class GetSubjectDetailController {

  private final GetSubjectDetailQuery getSubjectDetailQuery;

  @Operation(summary = "특정 SUBJECT 조회", description = "특정 SUBJECT 를 조회할 수 있습니다.")
  @GetMapping("/{uuid}")
  public ApiResponse<GetSubjectDetailWebResponse> getSubjectDetail(
      @Parameter(description = "SUBJECT UUID")
      final @PathVariable @NotBlank String uuid
  ) {
    final var inResponse = getSubjectDetailQuery.loadById(uuid);

    final var webResponse = GetSubjectDetailWebResponse.of(inResponse);

    return ApiResponseGenerator.success(webResponse);
  }
}
