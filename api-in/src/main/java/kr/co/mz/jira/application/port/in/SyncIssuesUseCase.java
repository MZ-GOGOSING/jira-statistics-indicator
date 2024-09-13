package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.Min;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;

public interface SyncIssuesUseCase {

  SyncSearchResultInResponse sync(final @Min(1L) Long subjectId);
}
