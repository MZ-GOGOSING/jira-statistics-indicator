package kr.co.mz.jira.jpa.request.query;

import kr.co.mz.support.dto.LocalDateRangeQuery;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SubjectJpaFetchQuery {

  private final String contents;

  private final LocalDateRangeQuery registeredPeriod;
}
