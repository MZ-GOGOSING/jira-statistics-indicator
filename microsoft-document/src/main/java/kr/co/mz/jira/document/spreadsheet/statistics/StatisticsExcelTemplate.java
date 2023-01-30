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
          @ColumnWidth(column = 0, width = 20),   // 식별자
          @ColumnWidth(column = 1, width = 150),  // 요약
          @ColumnWidth(column = 2, width = 20),   // 유형
          @ColumnWidth(column = 3, width = 20),   // 라벨
          @ColumnWidth(column = 4, width = 20),   // 상태
          @ColumnWidth(column = 5, width = 20),   // 담당자
          @ColumnWidth(column = 6, width = 20),   // 보고자
          @ColumnWidth(column = 7, width = 25),   // 생성일
          @ColumnWidth(column = 8, width = 25),   // 최종수정일
          @ColumnWidth(column = 9, width = 25),   // 목표일
          @ColumnWidth(column = 10, width = 40)   // 총 작업 소요시간
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
