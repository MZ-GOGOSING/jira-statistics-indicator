package kr.co.mz.jira.application.port.in;

import javax.validation.constraints.NotBlank;

public interface CreateSearchResultUseCase {

  void save(final @NotBlank String jql);
}
