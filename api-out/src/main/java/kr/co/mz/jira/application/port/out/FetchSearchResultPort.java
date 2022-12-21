package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.domain.SubjectDomainEntity;

public interface FetchSearchResultPort {

  SubjectDomainEntity fetchByJql(final @NotBlank String jql);
}
