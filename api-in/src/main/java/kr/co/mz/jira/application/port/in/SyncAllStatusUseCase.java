package kr.co.mz.jira.application.port.in;

import kr.co.mz.jira.application.port.in.response.SyncAllStatusInResponse;

import java.time.LocalDate;

public interface SyncAllStatusUseCase {

  SyncAllStatusInResponse sync(final LocalDate syncDate);
}
