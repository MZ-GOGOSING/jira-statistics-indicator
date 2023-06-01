package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;

public interface SyncSearchResultUseCase {

  SyncSearchResultInResponse sync(final @NotBlank String jql);
  void syncIssueLog(String uuid);
  void deleteIssueWorkerLog(String worker, String workDate);
  void deleteIssueWorkerLog(String worker, String startDt, String endDt);
  String selectWorkerLog(String worker, String workDate);
  String selectWorkerLog(String worker, String startDt, String endDt);
}
