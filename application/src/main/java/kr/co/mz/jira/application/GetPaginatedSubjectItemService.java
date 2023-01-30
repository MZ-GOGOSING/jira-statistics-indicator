package kr.co.mz.jira.application;

import kr.co.mz.jira.application.port.in.GetPaginatedSubjectItemQuery;
import kr.co.mz.jira.application.port.in.request.query.GetPaginatedSubjectItemInQuery;
import kr.co.mz.jira.application.port.in.response.GetSubjectItemInResponse;
import kr.co.mz.jira.application.port.out.LoadPaginatedSubjectItemPort;
import kr.co.mz.jira.application.port.out.request.query.GetPaginatedSubjectItemOutQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class GetPaginatedSubjectItemService implements GetPaginatedSubjectItemQuery {

  private final LoadPaginatedSubjectItemPort loadPaginatedSubjectItemPort;

  @Override
  public Page<GetSubjectItemInResponse> loadAll(
      final GetPaginatedSubjectItemInQuery inQuery,
      final Pageable pageable
  ) {
    final var outQuery = this.convertToOutQuery(inQuery);
    final var outResponse = loadPaginatedSubjectItemPort.findAll(outQuery, pageable);

    return outResponse.map(GetSubjectItemInResponse::of);
  }

  private GetPaginatedSubjectItemOutQuery convertToOutQuery(final GetPaginatedSubjectItemInQuery inQuery) {
    return GetPaginatedSubjectItemOutQuery.builder()
        .contents(inQuery.getContents())
        .registeredPeriod(inQuery.getRegisteredPeriod())
        .build();
  }
}
