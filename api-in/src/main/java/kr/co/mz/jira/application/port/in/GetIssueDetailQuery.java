package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.Min;
import kr.co.mz.jira.application.port.in.response.GetIssueDetailInResponse;

public interface GetIssueDetailQuery {

  GetIssueDetailInResponse loadById(final @Min(1L) Long id);
}
