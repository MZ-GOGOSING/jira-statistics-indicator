package kr.co.mz.jira.jpa.repository;

import java.util.List;
import kr.co.mz.jira.jpa.domain.IssueWorkerLogDto;

public interface IssueWorklogJpaRepositoryCustom {
    //
    List<IssueWorkerLogDto> selectIssueWorkerLog(Long subjectId);
}
