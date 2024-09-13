package kr.co.mz.jira.application.port.out;

import javax.validation.constraints.Min;
import kr.co.mz.jira.domain.SubjectDomainEntity;

public interface LoadSubjectPort {

  SubjectDomainEntity findById(final @Min(1L) Long subjectId);
}
