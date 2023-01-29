package kr.co.mz.jira.application.port.in;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.code.IssueStatus;

public interface GetSubjectDocumentQuery {

  byte[] loadByUuid(
      final @NotBlank String uuid,
      final @NotEmpty List<IssueStatus> workflow
  );
}
