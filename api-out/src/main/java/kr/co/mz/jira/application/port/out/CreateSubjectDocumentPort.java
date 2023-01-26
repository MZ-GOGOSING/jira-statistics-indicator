package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface CreateSubjectDocumentPort {

  byte[] create(final @NotEmpty List<IssueDomainEntity> issueDomainEntities);
}
