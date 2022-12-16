package kr.co.mz.jira.support.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import kr.co.mz.jira.support.converter.DefaultDateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 기간 필터링 조건 모델.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class LocalDateTimeRangeQuery {

  /**
   * 필터링 적용 시작일.
   */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = DefaultDateTimeFormat.DATE_TIME_FORMAT_PATTERN)
  private LocalDateTime startDateTime;

  /**
   * 필터링 적용 종료일).
   */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(pattern = DefaultDateTimeFormat.DATE_TIME_FORMAT_PATTERN)
  private LocalDateTime endDateTime;
}
