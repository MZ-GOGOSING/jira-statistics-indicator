package kr.co.mz.jira.application.port.out;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public interface DeleteAllStatusPort {

  void deleteAllBySyncDate(final @NotNull LocalDate syncDate);
}
