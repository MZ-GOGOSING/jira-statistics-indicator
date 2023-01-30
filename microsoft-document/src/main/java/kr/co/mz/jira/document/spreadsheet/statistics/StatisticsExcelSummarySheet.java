package kr.co.mz.jira.document.spreadsheet.statistics;

import com.mz.poi.mapper.annotation.CellStyle;
import com.mz.poi.mapper.annotation.DataRows;
import com.mz.poi.mapper.annotation.Header;
import com.mz.poi.mapper.annotation.Match;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsExcelSummarySheet {

  @DataRows(
      match = Match.STOP_ON_BLANK,
      headers = {
          @Header(name = "식별자", mappings = {"key"}),
          @Header(name = "요약", mappings = {"summary"}),
          @Header(name = "유형", mappings = {"type"}),
          @Header(name = "라벨", mappings = {"labels"}),
          @Header(name = "상태", mappings = {"status"}),
          @Header(name = "담당자", mappings = {"assignee"}),
          @Header(name = "보고자", mappings = {"reporter"}),
          @Header(name = "생성일", mappings = {"createdDate"}),
          @Header(name = "최종수정일", mappings = {"lastModifiedDate"}),
          @Header(name = "목표일", mappings = {"dueDate"}),
          @Header(name = "총 작업 소요시간", mappings = {"timeSpentDurationWords"})
      },
      headerStyle = @CellStyle(
          borderTop = BorderStyle.THIN,
          borderBottom = BorderStyle.THIN,
          borderLeft = BorderStyle.THIN,
          borderRight = BorderStyle.THIN,
          alignment = HorizontalAlignment.CENTER,
          verticalAlignment = VerticalAlignment.CENTER
      ),
      dataStyle = @CellStyle(
          borderTop = BorderStyle.THIN,
          borderBottom = BorderStyle.THIN,
          borderLeft = BorderStyle.THIN,
          borderRight = BorderStyle.THIN
      )
  )
  private List<StatisticsExcelSummaryRow> items;
}
