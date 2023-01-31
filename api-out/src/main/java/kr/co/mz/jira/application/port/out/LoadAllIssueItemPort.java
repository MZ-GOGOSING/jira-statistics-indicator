package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.constraints.Min;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface LoadAllIssueItemPort {

  List<IssueDomainEntity> findAllBySubjectId(final @Min(1L) Long subjectId);
}
