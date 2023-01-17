package kr.co.mz.jira.adapter.out.persistence;

import kr.co.mz.jira.adapter.out.persistence.converter.domain.SubjectDomainEntityConverter;
import kr.co.mz.jira.application.port.out.LoadPaginatedSubjectItemPort;
import kr.co.mz.jira.application.port.out.request.query.GetPaginatedSubjectItemOutQuery;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.SubjectJpaRepository;
import kr.co.mz.jira.jpa.request.query.SubjectJpaFetchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoadPaginatedSubjectItemPersistenceAdapter implements LoadPaginatedSubjectItemPort {

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private final SubjectJpaRepository subjectJpaRepository;

  @Override
  public Page<SubjectDomainEntity> findAll(
      final GetPaginatedSubjectItemOutQuery outQuery,
      final Pageable pageable
  ) {
    final var jpaQuery = this.convertToJpaQuery(outQuery);

    final var paginatedJpaEntities = subjectJpaRepository
        .findAllByFetchQuery(jpaQuery, pageable);

    return paginatedJpaEntities.map(SUBJECT_DOMAIN_ENTITY_CONVERTER::convert);
  }

  private SubjectJpaFetchQuery convertToJpaQuery(final GetPaginatedSubjectItemOutQuery outQuery) {
    return SubjectJpaFetchQuery.builder()
        .contents(outQuery.getContents())
        .registeredPeriod(outQuery.getRegisteredPeriod())
        .build();
  }
}
