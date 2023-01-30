package kr.co.mz.jira.application.port.in.request.query;

import javax.validation.Valid;
import kr.co.mz.support.dto.LocalDateRangeQuery;
import kr.co.mz.support.validation.BetweenDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetPaginatedSubjectItemInQuery {

  private static final int PERIOD_MAXIMUM_SEARCH_RANGE = 365;

  private final String contents;

  @Valid
  @BetweenDate(maximumDateRangeLimit = PERIOD_MAXIMUM_SEARCH_RANGE)
  private final LocalDateRangeQuery registeredPeriod;
}
