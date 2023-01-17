package kr.co.mz.jira.application.port.in;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.application.port.in.request.GetPaginatedSubjectItemInQuery;
import kr.co.mz.jira.application.port.in.response.GetSubjectItemInResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPaginatedSubjectItemQuery {

  Page<GetSubjectItemInResponse> loadAll(
      final @Valid GetPaginatedSubjectItemInQuery inQuery,
      final @NotNull Pageable pageable
  );
}
