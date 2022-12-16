package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import kr.co.mz.jira.support.dto.ApiResponse;
import kr.co.mz.jira.support.dto.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "STATISTICS", description = "통계 관리 API")
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class GetDummyController {

  @Operation(summary = "특정 DUMMY 조회", description = "특정 DUMMY 를 조회할 수 있습니다.")
  @GetMapping
  public ApiResponse<Map<String, String>> getDummy() {
    return ApiResponseGenerator.success(Map.of("id", "hello-world"));
  }
}
