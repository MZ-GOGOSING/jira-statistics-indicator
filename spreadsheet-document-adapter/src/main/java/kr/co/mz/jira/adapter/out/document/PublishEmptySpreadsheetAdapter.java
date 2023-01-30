package kr.co.mz.jira.adapter.out.document;

import com.mz.poi.mapper.ExcelMapper;
import java.io.IOException;
import java.util.List;
import kr.co.mz.jira.application.port.out.PublishEmptyDocumentPort;
import kr.co.mz.jira.document.spreadsheet.empty.EmptyExcelRow;
import kr.co.mz.jira.document.spreadsheet.empty.EmptyExcelSheet;
import kr.co.mz.jira.document.spreadsheet.empty.EmptyExcelTemplate;
import kr.co.mz.support.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class PublishEmptySpreadsheetAdapter implements
    PublishEmptyDocumentPort,
    SpreadsheetByteArraySupport {

  @Override
  public byte[] publish(final String defaultMessage) {
    try (final var workbook = this.buildWorkbook(defaultMessage)) {
      return this.convertWorkbookToByteArray(workbook);
    } catch (IOException ioException) {
      throw new BusinessException("spreadsheet 문서 생성 중 오류 발생", ioException);
    }
  }

  private Workbook buildWorkbook(final String defaultMessage) {
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
