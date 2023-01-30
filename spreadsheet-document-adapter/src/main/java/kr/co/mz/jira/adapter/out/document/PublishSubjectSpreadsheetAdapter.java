package kr.co.mz.jira.adapter.out.document;

import com.mz.poi.mapper.ExcelMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.document.converter.StatisticsExcelSummaryRowConverter;
import kr.co.mz.jira.application.port.out.PublishSubjectDocumentPort;
import kr.co.mz.jira.application.port.out.request.command.PublishSubjectDocumentOutCommand;
import kr.co.mz.jira.code.IssueStatus;
import kr.co.mz.jira.document.spreadsheet.statistics.StatisticsExcelDetailRow;
import kr.co.mz.jira.document.spreadsheet.statistics.StatisticsExcelDetailSheet;
import kr.co.mz.jira.document.spreadsheet.statistics.StatisticsExcelSummarySheet;
import kr.co.mz.jira.document.spreadsheet.statistics.StatisticsExcelTemplate;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.support.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class PublishSubjectSpreadsheetAdapter implements
    PublishSubjectDocumentPort,
    SpreadsheetByteArraySupport {

  private static final StatisticsExcelSummaryRowConverter STATISTICS_EXCEL_SUMMARY_ROW_CONVERTER =
      new StatisticsExcelSummaryRowConverter();

  @Override
  public byte[] publish(final PublishSubjectDocumentOutCommand outCommand) {
    try (final var workbook = this.buildWorkbook(outCommand)) {
      return this.convertWorkbookToByteArray(workbook);
    } catch (IOException ioException) {
      throw new BusinessException("spreadsheet 문서 생성 중 오류 발생", ioException);
    }
  }

  private Workbook buildWorkbook(final PublishSubjectDocumentOutCommand outCommand) {
    final var excelTemplate = this.buildTemplate(outCommand);

    return ExcelMapper.toExcel(excelTemplate, new SXSSFWorkbook());
  }

  private StatisticsExcelTemplate buildTemplate(final PublishSubjectDocumentOutCommand outCommand) {
    final var summarySheet = this.buildSummarySheet(outCommand.getIssueDomainEntities());
    final var detailSheet = this.buildDetailSheet(outCommand.getIssueDomainEntities(), outCommand.getWorkflow());

    return StatisticsExcelTemplate.builder()
        .summarySheet(summarySheet)
        .detailSheet(detailSheet)
        .build();
  }

  private StatisticsExcelSummarySheet buildSummarySheet(final List<IssueDomainEntity> issueDomainEntities) {
    final var summaryRowList = issueDomainEntities
        .stream()
        .map(STATISTICS_EXCEL_SUMMARY_ROW_CONVERTER::convert)
        .collect(Collectors.toList());

    return StatisticsExcelSummarySheet.builder()
        .items(summaryRowList)
        .build();
  }

  private StatisticsExcelDetailSheet buildDetailSheet(
      final List<IssueDomainEntity> issueDomainEntities,
      final List<IssueStatus> workflow
  ) {
    return StatisticsExcelDetailSheet.builder()
        .items(List.of(new StatisticsExcelDetailRow()))
        .build();
  }
}
