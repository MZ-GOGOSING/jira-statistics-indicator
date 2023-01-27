package kr.co.mz.jira.document.spreadsheet.statistics;

import static org.apache.poi.ss.usermodel.PrintSetup.A4_PAPERSIZE;

import com.mz.poi.mapper.annotation.CellStyle;
import com.mz.poi.mapper.annotation.ColumnWidth;
import com.mz.poi.mapper.annotation.Excel;
import com.mz.poi.mapper.annotation.Font;
import com.mz.poi.mapper.annotation.PrintSetup;
import com.mz.poi.mapper.annotation.Sheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Excel(
    defaultStyle = @CellStyle(
        font = @Font(fontName = "Arial")
    ),
    dateFormatZoneId = "Asia/Seoul"
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsExcelTemplate {

  private static final String STATISTICS_SHEET_NAME = "sheet1";

  @Sheet(
      name = STATISTICS_SHEET_NAME,
      index = 0,
      columnWidths = {
          @ColumnWidth(column = 0, width = 20)
      },
      fitToPage = true,
      printSetup = @PrintSetup(paperSize = A4_PAPERSIZE)
  )
  private StatisticsExcelSheet sheet;
}
