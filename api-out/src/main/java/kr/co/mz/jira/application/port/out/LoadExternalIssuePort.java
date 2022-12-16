package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.NotBlank;

public interface LoadExternalIssuePort {

  void loadAllByJql(final @NotBlank String jql);
}
