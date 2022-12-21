package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;

public interface SyncSearchResultUseCase {

  SyncSearchResultInResponse sync(final @NotBlank String jql);
}
