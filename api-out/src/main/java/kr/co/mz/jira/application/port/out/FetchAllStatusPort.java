package kr.co.mz.jira.application.port.out;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.StatusDomainEntity;

public interface FetchAllStatusPort {

  List<StatusDomainEntity> fetchAll(final @NotNull LocalDate syncDate);
}
