package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.Valid;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface CreateAllIssuePort {

    List<IssueDomainEntity> saveAll(final @Valid CreateAllIssueOutCommand outCommand);
    void syncIssueLog(String uuid);
    void deleteIssueWorkerLog(String worker, String workDate);
    void deleteIssueWorkerLogForBoundary(String worker, String strDate, String endDate);
    String selectWorkerLog(String worker, String workDate);
    String selectWorkerLogForBoundary(String worker, String strDate, String endDate);
}
