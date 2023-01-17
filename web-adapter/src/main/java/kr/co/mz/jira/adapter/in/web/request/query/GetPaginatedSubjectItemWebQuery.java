package kr.co.mz.jira.adapter.in.web.request.query;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import kr.co.mz.jira.application.port.in.request.GetPaginatedSubjectItemInQuery;
import kr.co.mz.jira.support.dto.LocalDateRangeQuery;
import kr.co.mz.jira.support.validation.BetweenDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

@Schema(description = "SUBJECT 목록 조회 조건 정보 모델")
@ParameterObject
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetPaginatedSubjectItemWebQuery {

  private static final int PERIOD_MAXIMUM_SEARCH_RANGE = 365;

  @Parameter(description = "IssueKey 검색 내용 (like)")
  private String contents;

  @Builder.Default
  @Valid
  @BetweenDate(maximumDateRangeLimit = PERIOD_MAXIMUM_SEARCH_RANGE)
  @Parameter(description = "검색 등록기간 (between)", required = true)
  private LocalDateRangeQuery registeredPeriod =
      new LocalDateRangeQuery(PERIOD_MAXIMUM_SEARCH_RANGE);

  public GetPaginatedSubjectItemInQuery toInQuery() {
    return GetPaginatedSubjectItemInQuery.builder()
        .contents(this.contents)
        .registeredPeriod(this.registeredPeriod)
        .build();
  }
}
