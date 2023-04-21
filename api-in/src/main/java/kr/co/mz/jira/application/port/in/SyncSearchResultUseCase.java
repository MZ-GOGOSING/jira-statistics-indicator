package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;

public interface SyncSearchResultUseCase {

  SyncSearchResultInResponse sync(final @NotBlank String jql);
  void syncIssueLog(String uuid);
  void deleteIssueWorkerLog(String worker, String workDate);
  void deleteIssueWorkerLogForBoundary(String worker, String strDate, String endDate);
  String selectWorkerLog(String worker, String workDate);
  String selectWorkerLogForBoundary(String worker, String strDate, String endDate);
}
