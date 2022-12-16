package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.CreateSearchResultUseCase;
import kr.co.mz.jira.application.port.out.LoadExternalIssuePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateSearchResultService implements CreateSearchResultUseCase {

  private final LoadExternalIssuePort loadExternalIssuePort;

  @Override
  public void save(final String jql) {
    loadExternalIssuePort.loadAllByJql(jql);
  }
}
