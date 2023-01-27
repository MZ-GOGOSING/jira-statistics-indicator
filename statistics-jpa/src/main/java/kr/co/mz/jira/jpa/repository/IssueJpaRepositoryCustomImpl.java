package kr.co.mz.jira.jpa.repository;

import static com.querydsl.core.types.dsl.Expressions.allOf;

import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import kr.co.mz.jira.jpa.config.StatisticsJpaRepositorySupport;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueChangelogItemJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueTimeTrackingJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueWorklogJpaEntity;

@SuppressWarnings("unused")
public class IssueJpaRepositoryCustomImpl extends StatisticsJpaRepositorySupport
    implements IssueJpaRepositoryCustom {

  private static final QIssueJpaEntity Q_ISSUE_JPA_ENTITY = QIssueJpaEntity.issueJpaEntity;

  private static final QIssueTimeTrackingJpaEntity Q_ISSUE_TIME_TRACKING_JPA_ENTITY =
      QIssueTimeTrackingJpaEntity.issueTimeTrackingJpaEntity;

  private static final QIssueWorklogJpaEntity Q_ISSUE_WORKLOG_JPA_ENTITY =
      QIssueWorklogJpaEntity.issueWorklogJpaEntity;

  private static final QIssueChangelogGroupJpaEntity Q_ISSUE_CHANGELOG_GROUP_JPA_ENTITY =
      QIssueChangelogGroupJpaEntity.issueChangelogGroupJpaEntity;

  private static final QIssueChangelogItemJpaEntity Q_ISSUE_CHANGELOG_ITEM_JPA_ENTITY =
      QIssueChangelogItemJpaEntity.issueChangelogItemJpaEntity;

  public IssueJpaRepositoryCustomImpl() {
    super(IssueJpaEntity.class);
  }

  @Override
  public List<IssueJpaEntity> findAllBySubjectIdAndField(final Long subjectId, final String field) {
    final var jpqlQuery = this.getDefaultFetchJpqlQuery();
    final var whereClause = allOf(
        Q_ISSUE_JPA_ENTITY.subjectId.eq(subjectId),
        Q_ISSUE_CHANGELOG_ITEM_JPA_ENTITY.field.containsIgnoreCase(field)
    );

    return jpqlQuery.where(whereClause).distinct().fetch();
  }

  private JPQLQuery<IssueJpaEntity> getDefaultFetchJpqlQuery() {
    return from(Q_ISSUE_JPA_ENTITY)
        .innerJoin(Q_ISSUE_JPA_ENTITY.timeTracking, Q_ISSUE_TIME_TRACKING_JPA_ENTITY)
        .fetchJoin()
        .leftJoin(Q_ISSUE_JPA_ENTITY.worklogs, Q_ISSUE_WORKLOG_JPA_ENTITY)
        .fetchJoin()
        .leftJoin(Q_ISSUE_JPA_ENTITY.changelog, Q_ISSUE_CHANGELOG_GROUP_JPA_ENTITY)
        .fetchJoin()
        .leftJoin(Q_ISSUE_CHANGELOG_GROUP_JPA_ENTITY.items, Q_ISSUE_CHANGELOG_ITEM_JPA_ENTITY)
        .fetchJoin();
  }
}
