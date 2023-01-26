package kr.co.mz.jira.jpa.repository;

import static java.util.Collections.emptyList;
import static kr.co.mz.support.query.QueryDslHelper.optionalWhen;

import com.querydsl.jpa.JPQLQuery;
import kr.co.mz.jira.jpa.config.StatisticsJpaRepositorySupport;
import kr.co.mz.jira.jpa.entity.QSubjectJpaEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.request.query.SubjectJpaFetchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unused")
public class SubjectJpaRepositoryCustomImpl extends StatisticsJpaRepositorySupport
    implements SubjectJpaRepositoryCustom {

  private static final QSubjectJpaEntity Q_SUBJECT_JPA_ENTITY = QSubjectJpaEntity.subjectJpaEntity;

  public SubjectJpaRepositoryCustomImpl() {
    super(SubjectJpaEntity.class);
  }

  @Override
  public Page<SubjectJpaEntity> findAllByFetchQuery(
      final SubjectJpaFetchQuery jpaFetchQuery,
      final Pageable pageable
  ) {
    final var jpqlQuery = getDefaultFetchJpqlQuery();

    this.applyFetchWhereClause(jpqlQuery, jpaFetchQuery);

    final var totalCount = jpqlQuery.fetchCount();
    if (totalCount < 1L) {
      return new PageImpl<>(emptyList(), pageable, totalCount);
    }

    final var content = getQuerydsl()
        .applyPagination(pageable, jpqlQuery)
        .fetch();

    return new PageImpl<>(content, pageable, totalCount);
  }

  private JPQLQuery<SubjectJpaEntity> getDefaultFetchJpqlQuery() {
    return getQuerydsl()
        .createQuery()
        .select(Q_SUBJECT_JPA_ENTITY)
        .from(Q_SUBJECT_JPA_ENTITY);
  }

  private void applyFetchWhereClause(
      final JPQLQuery<SubjectJpaEntity> jpqlQuery,
      final SubjectJpaFetchQuery jpaFetchQuery
  ) {
    optionalWhen(jpaFetchQuery.getRegisteredPeriod()).then(
        period -> {
          optionalWhen(period.getStartDateTime()).then(
              startDateTime -> jpqlQuery.where(Q_SUBJECT_JPA_ENTITY.createdDate.goe(startDateTime))
          );
          optionalWhen(period.getEndDateTime()).then(
              endDateTime -> jpqlQuery.where(Q_SUBJECT_JPA_ENTITY.createdDate.loe(endDateTime))
          );
        }
    );
    optionalWhen(jpaFetchQuery.getContents()).then(
        it -> jpqlQuery.where(Q_SUBJECT_JPA_ENTITY.rawJqlResult.containsIgnoreCase(it))
    );
  }
}
