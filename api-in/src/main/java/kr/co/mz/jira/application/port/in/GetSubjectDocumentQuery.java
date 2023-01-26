package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;

public interface GetSubjectDocumentQuery {

  byte[] loadByUuid(final @NotBlank String uuid);
}
