package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.application.port.in.response.GetSubjectDetailInResponse;

public interface GetSubjectDetailQuery {

  GetSubjectDetailInResponse loadById(final @NotBlank String uuid);
}
