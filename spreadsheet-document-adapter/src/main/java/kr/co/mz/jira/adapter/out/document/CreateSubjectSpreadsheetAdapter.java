package kr.co.mz.jira.adapter.out.document;

import java.io.IOException;
import java.util.List;
import kr.co.mz.jira.application.port.out.CreateSubjectDocumentPort;
import kr.co.mz.jira.code.IssueStatus;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.support.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class CreateSubjectSpreadsheetAdapter implements
    CreateSubjectDocumentPort,
    SpreadsheetByteArraySupport {

  @Override
  public byte[] create(
      final List<IssueDomainEntity> issueDomainEntities,
      final List<IssueStatus> workflow
  ) {
    try (final var workbook = this.buildWorkbook(issueDomainEntities)) {
      return this.convertWorkbookToByteArray(workbook);
    } catch (IOException ioException) {
      throw new BusinessException("spreadsheet 문서 생성 중 오류 발생", ioException);
    }
  }

  private Workbook buildWorkbook(final List<IssueDomainEntity> issueDomainEntities) {
    /*
    final var workbook = new SXSSFWorkbook();
    final var excelTemplate = EmptyExcelTemplate.builder()
        .sheet(
            EmptyExcelSheet.builder()
                .items(List.of(new EmptyExcelRow(defaultMessage)))
                .build()
        )
        .build();

    return ExcelMapper.toExcel(excelTemplate, workbook);
    */
    return null;
  }
}
