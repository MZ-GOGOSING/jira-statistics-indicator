package kr.co.mz.jira.application.port.in;

import kr.co.mz.jira.application.port.in.response.SyncAllStatusInResponse;

public interface SyncAllStatusUseCase {

  SyncAllStatusInResponse sync();
}
