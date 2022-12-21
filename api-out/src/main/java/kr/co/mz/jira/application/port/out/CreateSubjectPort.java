package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.SubjectDomainEntity;

public interface CreateSubjectPort {

  SubjectDomainEntity save(final @NotNull SubjectDomainEntity outCommand);
}
