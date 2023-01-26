package kr.co.mz.jira.document.spreadsheet.empty;

import com.mz.poi.mapper.ExcelMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateEmptySpreadsheetService {

  public Workbook create(final String defaultMessage) {
    final var workbook = new SXSSFWorkbook();
    final var excelTemplate = EmptyExcelTemplate.builder()
        .sheet(
            EmptyExcelSheet.builder()
                .items(List.of(new EmptyExcelRow(defaultMessage)))
                .build()
        )
        .build();

    return ExcelMapper.toExcel(excelTemplate, workbook);
  }
}
