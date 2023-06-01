package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.Valid;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface CreateAllIssuePort {

    List<IssueDomainEntity> saveAll(final @Valid CreateAllIssueOutCommand outCommand);
    void syncIssueLog(String uuid);
    void deleteIssueWorkerLog(String worker, String workDate);
    void deleteIssueWorkerLog(String worker, String startDt, String endDt);
    String selectWorkerLog(String worker, String workDate);
    String selectWorkerLog(String worker, String startDt, String endDt);
}
