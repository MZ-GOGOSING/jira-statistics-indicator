package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;

public interface GetSearchResultQuery {

  void loadByJql(final @NotBlank String jql);
}
