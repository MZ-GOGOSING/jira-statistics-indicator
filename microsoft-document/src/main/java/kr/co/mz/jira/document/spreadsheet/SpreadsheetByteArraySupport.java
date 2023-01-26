package kr.co.mz.jira.document.spreadsheet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kr.co.mz.jira.support.exception.BusinessException;
import org.apache.poi.ss.usermodel.Workbook;

public interface SpreadsheetByteArraySupport {

  default byte[] convertWorkbookToByteArray(final Workbook workbook) {
    try (final var byteArrayOutputStream = new ByteArrayOutputStream()) {
      workbook.write(byteArrayOutputStream);
      return byteArrayOutputStream.toByteArray();
    } catch (IOException ioException) {
      throw new BusinessException("spreadsheet 문서를 비이트화 하던 중 오류 발생", ioException);
    }
  }
}
