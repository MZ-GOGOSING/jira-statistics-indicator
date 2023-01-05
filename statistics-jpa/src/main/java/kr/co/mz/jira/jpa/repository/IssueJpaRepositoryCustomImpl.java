package kr.co.mz.jira.jpa.repository;

import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import kr.co.mz.jira.jpa.config.StatisticsJpaRepositorySupport;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDto;
import kr.co.mz.jira.jpa.domain.QIssueStatusLogDto;
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
                        issueChangeLogItem.toString,
                        issueChangeLogGroup.created
                ))
                .from(issue)
                .join(issueChangeLogGroup)
                .on(issue.id.eq(issueChangeLogGroup.issue.id))
                .join(issueChangeLogItem)
                .on(issueChangeLogGroup.id.eq(issueChangeLogItem.issueChangelogGroup.id))
                .where(
                        issue.subjectId.eq(subjectId)
                );
        return jpqlQuery.fetchJoin().fetch();
    }
}
