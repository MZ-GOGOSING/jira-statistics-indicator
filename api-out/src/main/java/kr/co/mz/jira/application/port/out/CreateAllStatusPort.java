package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.domain.StatusDomainEntity;

public interface CreateAllStatusPort {

  List<StatusDomainEntity> saveAll(final @NotEmpty List<StatusDomainEntity> statusDomainEntities);
}
