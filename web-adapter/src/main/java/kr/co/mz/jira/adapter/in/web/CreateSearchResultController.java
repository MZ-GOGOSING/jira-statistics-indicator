package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import javax.validation.Valid;
import kr.co.mz.jira.adapter.in.web.request.CreateSearchResultWebCommand;
import kr.co.mz.jira.adapter.in.web.response.CreateSearchResultWebResponse;
import kr.co.mz.jira.application.port.in.CreateSearchResultUseCase;
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
public class CreateSearchResultController {

  private final CreateSearchResultUseCase createSearchResultUseCase;

  @Operation(summary = "검색결과 동기화", description = "JQL 검색결과로 얻어진 Issue 목록을 DB 에 동기화 합니다.")
  @PostMapping
  public ApiResponse<CreateSearchResultWebResponse> postSearchResult(
      final @RequestBody @Valid CreateSearchResultWebCommand createBoardArticleWebCommand
  ) {
    createSearchResultUseCase.save(createBoardArticleWebCommand.getJql());

    final var dummyWebResponse = CreateSearchResultWebResponse.of();

    return ApiResponseGenerator.success(dummyWebResponse);
  }
}
