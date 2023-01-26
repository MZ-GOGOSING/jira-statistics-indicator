package kr.co.mz.jira.adapter.out.document;

import java.io.IOException;
import kr.co.mz.jira.application.port.out.CreateEmptyDocumentPort;
import kr.co.mz.jira.document.spreadsheet.SpreadsheetByteArraySupport;
import kr.co.mz.jira.document.spreadsheet.empty.CreateEmptySpreadsheetService;
import kr.co.mz.jira.support.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateEmptySpreadsheetAdapter implements
    CreateEmptyDocumentPort,
    SpreadsheetByteArraySupport {

  private final CreateEmptySpreadsheetService createEmptySpreadsheetService;

  @Override
  public byte[] create(final String defaultMessage) {
    try (final var workbook = createEmptySpreadsheetService.create(defaultMessage)) {
      return this.convertWorkbookToByteArray(workbook);
    } catch (IOException ioException) {
      throw new BusinessException("spreadsheet 문서 생성 중 오류 발생", ioException);
    }
  }
}
