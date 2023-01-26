package kr.co.mz.jira.document.spreadsheet.empty;

import com.mz.poi.mapper.annotation.Cell;
import com.mz.poi.mapper.annotation.CellStyle;
import com.mz.poi.mapper.structure.CellType;
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
public class EmptyExcelRow {

  /**
   * 부서코드.
   */
  @Cell(
      column = 0,
      cellType = CellType.STRING,
      style = @CellStyle(dataFormat = "#"),
      required = true
  )
  private String defaultMessage;
}
