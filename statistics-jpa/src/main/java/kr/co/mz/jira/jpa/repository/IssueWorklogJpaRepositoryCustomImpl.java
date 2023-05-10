package kr.co.mz.jira.jpa.repository;

import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import kr.co.mz.jira.jpa.config.StatisticsJpaRepositorySupport;
import kr.co.mz.jira.jpa.domain.IssueWorkerLogDto;
import kr.co.mz.jira.jpa.domain.QIssueWorkerLogDto;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueJpaEntity;
import kr.co.mz.jira.jpa.entity.QIssueWorklogJpaEntity;

public class IssueWorklogJpaRepositoryCustomImpl extends StatisticsJpaRepositorySupport
        implements IssueWorklogJpaRepositoryCustom {
    //

    private static final QIssueJpaEntity issue = QIssueJpaEntity.issueJpaEntity;
    private static final QIssueWorklogJpaEntity issueWorkLog
            = QIssueWorklogJpaEntity.issueWorklogJpaEntity;

    public IssueWorklogJpaRepositoryCustomImpl() {
        //
        super(IssueStatusLogJpaEntity.class);
    }

    @Override
    public List<IssueWorkerLogDto> selectIssueWorkerLog(Long subjectId) {
        //
        final JPQLQuery<IssueWorkerLogDto> jpqlQuery = getQuerydsl()
                .createQuery()
                .select(new QIssueWorkerLogDto(
                        issue.id,
                        issue.issueKey,
                        issueWorkLog.startDate,
                        issueWorkLog.authorUsername,
                        issueWorkLog.minutesSpent,
                        issueWorkLog.comment
                ))
                .from(issue)
                .join(issueWorkLog)
                .on(issue.id.eq(issueWorkLog.issue.id))
                .where(
                        issue.subjectId.eq(subjectId)
                );
        return jpqlQuery.fetchJoin().fetch();
    }
}
