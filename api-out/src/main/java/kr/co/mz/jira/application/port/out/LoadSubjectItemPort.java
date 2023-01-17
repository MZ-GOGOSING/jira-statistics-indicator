package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.domain.SubjectDomainEntity;

public interface LoadSubjectItemPort {

  SubjectDomainEntity findByUuid(final @NotBlank String uuid);
}
