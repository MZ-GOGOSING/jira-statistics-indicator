package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.request.query.SubjectJpaFetchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectJpaRepositoryCustom {

  Page<SubjectJpaEntity> findAllByFetchQuery(
      final SubjectJpaFetchQuery jpaFetchQuery,
      final Pageable pageable
  );
}
