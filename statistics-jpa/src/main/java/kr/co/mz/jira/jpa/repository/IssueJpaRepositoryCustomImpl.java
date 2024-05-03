package kr.co.mz.jira.jpa.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import kr.co.mz.jira.jpa.config.StatisticsJpaRepositorySupport;
import kr.co.mz.jira.jpa.domain.*;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueChangelogGroupJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueChangelogItemJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueJpaEntity;

public class IssueJpaRepositoryCustomImpl extends StatisticsJpaRepositorySupport
        implements IssueJpaRepositoryCustom {
    //

    private static final QIssueJpaEntity issue = QIssueJpaEntity.issueJpaEntity;
    private static final QIssueChangelogGroupJpaEntity issueChangeLogGroup
            = QIssueChangelogGroupJpaEntity.issueChangelogGroupJpaEntity;
    private static final QIssueChangelogItemJpaEntity issueChangeLogItem
            = QIssueChangelogItemJpaEntity.issueChangelogItemJpaEntity;

    public IssueJpaRepositoryCustomImpl() {
        //
        super(IssueStatusLogJpaEntity.class);
    }

    @Override
    public List<IssueStatusLogDto> selectIssueStatusLog(Long subjectId) {
        //
        final JPQLQuery<IssueStatusLogDto> jpqlQuery = getQuerydsl()
                .createQuery()
                .select(new QIssueStatusLogDto(
                        issue.id,
                        issue.issueKey,
                        issueChangeLogGroup.id,
                        issueChangeLogItem.toString,
                        issueChangeLogGroup.created
                ))
                .from(issue)
                .join(issueChangeLogGroup)
                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                .join(issueChangeLogItem)
                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id))
                .where(
                        issue.subjectId.eq(subjectId),
                        issueChangeLogItem.field.eq("status")
                )
                .orderBy(issueChangeLogGroup.created.asc());
        return jpqlQuery.fetchJoin().fetch();
    }

    @Override
    public List<IssueDelayedTimeLogDto> selectIssueDelayedTimeLog(Long subjectId) {
        //
        final JPQLQuery<IssueDelayedTimeLogDto> jpqlQuery = getQuerydsl()
                .createQuery()
                .select(new QIssueDelayedTimeLogDto(
                        issue.id,
                        issue.issueKey,
                        issueChangeLogItem.toString
                ))
                .from(issue)
                .join(issueChangeLogGroup)
                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                .join(issueChangeLogItem)
                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id))
                .where(
                        issue.subjectId.eq(subjectId),
                        issueChangeLogItem.field.eq("totalDelayedTime"),
                        issueChangeLogGroup.id.in(JPAExpressions.select(issueChangeLogGroup.id.max())
                                .from(issue)
                                .join(issueChangeLogGroup)
                                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                                .join(issueChangeLogItem)
                                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id).and(issueChangeLogItem.field.eq("totalDelayedTime")))
                                .where(
                                        issue.subjectId.eq(subjectId)
                                )
                                .groupBy(issue.id)
                        )
                )
                .groupBy()
                .orderBy(issueChangeLogGroup.created.asc());
        return jpqlQuery.fetchJoin().fetch();
    }

    @Override
    public List<IssueEndDateLogDto> selectIssueEndDateLog(Long subjectId) {
        final JPQLQuery<IssueEndDateLogDto> jpqlQuery = getQuerydsl()
                .createQuery()
                .select(new QIssueEndDateLogDto(
                        issue.id,
                        issue.issueKey,
                        issueChangeLogItem.toString
                ))
                .from(issue)
                .join(issueChangeLogGroup)
                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                .join(issueChangeLogItem)
                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id))
                .where(
                        issue.subjectId.eq(subjectId),
                        issueChangeLogItem.field.eq("End date"),
                        issueChangeLogGroup.id.in(JPAExpressions.select(issueChangeLogGroup.id.max())
                                .from(issue)
                                .join(issueChangeLogGroup)
                                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                                .join(issueChangeLogItem)
                                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id).and(issueChangeLogItem.field.eq("End date")))
                                .where(
                                        issue.subjectId.eq(subjectId)
                                )
                                .groupBy(issue.id)
                        )
                )
                .groupBy()
                .orderBy(issueChangeLogGroup.created.asc());
        return jpqlQuery.fetchJoin().fetch();
    }
}
