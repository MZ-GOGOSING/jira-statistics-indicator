package kr.co.mz.jira.application.port.out;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.application.port.out.request.query.GetPaginatedSubjectItemOutQuery;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadPaginatedSubjectItemPort {

  Page<SubjectDomainEntity> findAll(
      final @Valid GetPaginatedSubjectItemOutQuery outQuery,
      final @NotNull Pageable pageable
  );
}
