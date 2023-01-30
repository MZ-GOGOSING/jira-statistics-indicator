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

  /**
   * 식별자.
   */
  @Cell(
      column = 0,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String key;

  /**
   * 요약.
   */
  @Cell(
      column = 1,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String summary;

  /**
   * 유형.
   */
  @Cell(
      column = 2,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String type;

  /**
   * 라벨.
   */
  @Cell(
      column = 3,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String labels;

  /**
   * 상태.
   */
  @Cell(
      column = 4,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String status;

  /**
   * 담당자.
   */
  @Cell(
      column = 5,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String assignee;

  /**
   * 보고자.
   */
  @Cell(
      column = 6,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String reporter;

  /**
   * 생성일.
   */
  @Cell(
      column = 7,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss")
  )
  private LocalDateTime createdDate;

  /**
   * 최종수정일.
   */
  @Cell(
      column = 8,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss")
  )
  private LocalDateTime lastModifiedDate;

  /**
   * 목표일.
   */
  @Cell(
      column = 9,
      cellType = CellType.DATE,
      style = @CellStyle(dataFormat = "yyyy-MM-dd HH:mm:ss")
  )
  private LocalDateTime dueDate;

  /**
   * 총 작업 소요시간.
   */
  @Cell(
      column = 10,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#")
  )
  private String timeSpentDurationWords;
}
