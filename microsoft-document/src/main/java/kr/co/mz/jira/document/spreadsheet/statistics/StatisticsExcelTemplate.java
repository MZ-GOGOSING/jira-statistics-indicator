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

  private static final String SUMMARY_SHEET_NAME = "summary";

  private static final String DETAIL_SHEET_NAME = "detail";

  @Sheet(
      name = SUMMARY_SHEET_NAME,
      index = 0,
      columnWidths = {
          @ColumnWidth(column = 0, width = 20),   //
          @ColumnWidth(column = 1, width = 20),   //
          @ColumnWidth(column = 2, width = 20),   //
          @ColumnWidth(column = 3, width = 20),   //
          @ColumnWidth(column = 4, width = 20),   //
          @ColumnWidth(column = 5, width = 20),   //
          @ColumnWidth(column = 6, width = 20),   //
          @ColumnWidth(column = 7, width = 20),   //
          @ColumnWidth(column = 8, width = 20),   //
          @ColumnWidth(column = 9, width = 20),   //
          @ColumnWidth(column = 10, width = 20)   //
      },
      fitToPage = true,
      printSetup = @PrintSetup(paperSize = A4_PAPERSIZE)
  )
  private StatisticsExcelSummarySheet summarySheet;

  @Sheet(
      name = DETAIL_SHEET_NAME,
      index = 1,
      columnWidths = {
          @ColumnWidth(column = 0, width = 25)
      },
      fitToPage = true,
      printSetup = @PrintSetup(paperSize = A4_PAPERSIZE)
  )
  private StatisticsExcelDetailSheet detailSheet;
}
