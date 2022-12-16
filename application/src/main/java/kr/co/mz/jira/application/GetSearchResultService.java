package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.GetSearchResultQuery;
import kr.co.mz.jira.application.port.out.LoadSearchResultPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetSearchResultService implements GetSearchResultQuery {

  private final LoadSearchResultPort loadSearchResultPort;

  @Override
  public void loadByJql(final String jql) {
    loadSearchResultPort.loadAllByJql(jql);
  }
}
