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
          @Header(name = "", mappings = {"key"}),
          @Header(name = "", mappings = {"summary"}),
          @Header(name = "", mappings = {"type"}),
          @Header(name = "", mappings = {"labels"}),
          @Header(name = "", mappings = {"status"}),
          @Header(name = "", mappings = {"assignee"}),
          @Header(name = "", mappings = {"reporter"}),
          @Header(name = "", mappings = {"createdDate"}),
          @Header(name = "", mappings = {"lastModifiedDate"}),
          @Header(name = "", mappings = {"dueDate"}),
          @Header(name = "", mappings = {"timeSpentDurationWords"})
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
