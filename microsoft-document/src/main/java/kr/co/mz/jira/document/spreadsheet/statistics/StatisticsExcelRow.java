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
public class StatisticsExcelRow {

  private String key;

  private String summary;

  private String type;

  private String status;

  private String assignee;

  private String reporter;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;

  private String timeSpentDurationWords;

  //private String
}
