package kr.co.mz.jira.adapter.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import kr.co.mz.jira.adapter.in.web.request.query.GetPaginatedSubjectItemWebQuery;
import kr.co.mz.jira.adapter.in.web.response.GetSubjectItemWebResponse;
import kr.co.mz.jira.application.port.in.GetPaginatedSubjectItemQuery;
import kr.co.mz.support.dto.ApiResponse;
import kr.co.mz.support.dto.ApiResponseGenerator;
import kr.co.mz.support.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SEARCH", description = "통계 원천 데이터 조회 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class GetPaginatedSubjectItemController {

  private final GetPaginatedSubjectItemQuery getPaginatedSubjectItemQuery;

  @Operation(summary = "페이징 처리된 SUBJECT 목록 조회", description = "페이징 처리된 SUBJECT 목록을 조회할 수 있습니다.")
  @GetMapping("/subject")
  public ApiResponse<PageResponse<GetSubjectItemWebResponse>> getPaginatedSubjectItem(
      final @Valid GetPaginatedSubjectItemWebQuery webQuery,
      final @ParameterObject @PageableDefault(
          size = 15,
          sort = "id",
          direction = Direction.DESC
      ) Pageable pageable
  ) {
    final var inQuery = webQuery.toInQuery();
    final var inResponse = getPaginatedSubjectItemQuery.loadAll(inQuery, pageable);

    final var webResponse = inResponse.map(GetSubjectItemWebResponse::of);

    return ApiResponseGenerator.success(PageResponse.convert(webResponse));
  }
}
