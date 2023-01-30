package kr.co.mz.jira.document.spreadsheet.statistics;

import com.mz.poi.mapper.annotation.Cell;
import com.mz.poi.mapper.annotation.CellStyle;
import com.mz.poi.mapper.structure.CellType;
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
public class StatisticsExcelSummaryRow {

  @Cell(
      column = 0,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String key;

  @Cell(
      column = 1,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String summary;

  @Cell(
      column = 2,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String type;

  @Cell(
      column = 3,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String labels;

  @Cell(
      column = 4,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String status;

  @Cell(
      column = 5,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String assignee;

  @Cell(
      column = 6,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String reporter;

  @Cell(
      column = 7,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  )
  private LocalDateTime createdDate;

  @Cell(
      column = 8,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  )
  private LocalDateTime lastModifiedDate;

  @Cell(
      column = 9,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS")
  )
  private LocalDateTime dueDate;

  @Cell(
      column = 10,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String timeSpentDurationWords;
}
