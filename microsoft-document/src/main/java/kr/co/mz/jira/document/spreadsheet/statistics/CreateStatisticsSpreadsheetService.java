package kr.co.mz.jira.document.spreadsheet.statistics;

import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateStatisticsSpreadsheetService {

  public SXSSFWorkbook create() {
    return null;
  }
}
