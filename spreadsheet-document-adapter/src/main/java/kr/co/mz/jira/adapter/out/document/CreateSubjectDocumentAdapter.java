package kr.co.mz.jira.adapter.out.document;

import java.util.List;
import kr.co.mz.jira.application.port.out.CreateSubjectDocumentPort;
import kr.co.mz.jira.document.spreadsheet.statistics.CreateStatisticsSpreadsheetService;
import kr.co.mz.jira.domain.IssueDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateSubjectDocumentAdapter implements CreateSubjectDocumentPort {

  private final CreateStatisticsSpreadsheetService createStatisticsSpreadsheetService;

  @Override
  public byte[] create(final List<IssueDomainEntity> issueDomainEntities) {
    return new byte[0];
  }
}
