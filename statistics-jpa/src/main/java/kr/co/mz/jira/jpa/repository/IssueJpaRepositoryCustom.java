package kr.co.mz.jira.jpa.repository;

import java.util.List;

import kr.co.mz.jira.jpa.domain.IssueDelayedTimeLogDto;
import kr.co.mz.jira.jpa.domain.IssueEndDateLogDto;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDto;

public interface IssueJpaRepositoryCustom {
    //
    List<IssueStatusLogDto> selectIssueStatusLog(Long subjectId);

    List<IssueDelayedTimeLogDto> selectIssueDelayedTimeLog(Long subjectId);

    List<IssueEndDateLogDto> selectIssueEndDateLog(Long subjectId);
}
