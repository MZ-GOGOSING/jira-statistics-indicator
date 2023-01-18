package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.Min;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface LoadIssueItemPort {

  IssueDomainEntity findById(final @Min(1L) Long id);
}
