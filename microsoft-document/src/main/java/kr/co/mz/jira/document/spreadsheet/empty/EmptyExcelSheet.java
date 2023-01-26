package kr.co.mz.jira.document.spreadsheet.empty;

import com.mz.poi.mapper.annotation.CellStyle;
import com.mz.poi.mapper.annotation.DataRows;
import com.mz.poi.mapper.annotation.Match;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.BorderStyle;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmptyExcelSheet {

  @DataRows(
      hideHeader = true,
      match = Match.STOP_ON_BLANK,
      dataStyle = @CellStyle(
          borderTop = BorderStyle.THIN,
          borderBottom = BorderStyle.THIN,
          borderLeft = BorderStyle.THIN,
          borderRight = BorderStyle.THIN
      )
  )
  private List<EmptyExcelRow> items;
}
