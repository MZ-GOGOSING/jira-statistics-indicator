package kr.co.mz.jira.document.spreadsheet.statistics;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsExcelDetailRow {

  private LocalDateTime flow1;

  private LocalDateTime flow2;

  private LocalDateTime flow3;

  private LocalDateTime flow4;

  private LocalDateTime flow5;

  private LocalDateTime flow6;

  private LocalDateTime flow7;

  private LocalDateTime flow8;

  private LocalDateTime flow9;

  private LocalDateTime flow10;
}
