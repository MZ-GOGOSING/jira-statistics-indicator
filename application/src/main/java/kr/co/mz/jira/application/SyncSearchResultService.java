package kr.co.mz.jira.application;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kr.co.mz.jira.application.port.in.SyncSearchResultUseCase;
import kr.co.mz.jira.application.port.in.response.SyncSearchResultInResponse;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.CreateSubjectPort;
import kr.co.mz.jira.application.port.out.FetchAllIssuePort;
import kr.co.mz.jira.application.port.out.FetchSearchResultPort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.support.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class SyncSearchResultService implements SyncSearchResultUseCase {

    private final FetchSearchResultPort fetchSearchResultPort;

    private final FetchAllIssuePort fetchAllIssuePort;

    private final CreateSubjectPort createSubjectPort;

    private final CreateAllIssuePort createAllIssuePort;
    private final IssueRestClient issueRestClient;
    @Override
    public SyncSearchResultInResponse sync(final String jql) {
        SyncSearchResultInResponse response;
        try {
            final var subjectDomainEntity = this.saveSubject(jql);
            final var issueDomainEntities = this.saveAllIssues(
                    subjectDomainEntity.getId(), subjectDomainEntity.getIssueKeyList()
            );

            response = SyncSearchResultInResponse.of(subjectDomainEntity, issueDomainEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

        return response;
    }

    @Override
    public void syncIssueLog(String uuid) {
        createAllIssuePort.syncIssueLog(uuid);
    }

    @Override
    public void deleteIssueWorkerLog(String worker, String workDate) {
        createAllIssuePort.deleteIssueWorkerLog(worker, workDate);
    }

    @Override
    public void deleteIssueWorkerLog(String worker, String startDt, String endDt) {
        createAllIssuePort.deleteIssueWorkerLog(worker, startDt, endDt);
    }

    @Override
    public String selectWorkerLog(String worker, String workDate) {
        return createAllIssuePort.selectWorkerLog(worker, workDate);
    }

    @Override
    public String selectWorkerLog(String worker, String startDt, String endDt) {
        return createAllIssuePort.selectWorkerLog(worker, startDt, endDt);
    }

    private SubjectDomainEntity saveSubject(final String jql) {
        final var fetchedSubjectDomainEntity = fetchSearchResultPort.fetchByJql(jql);

        return createSubjectPort.save(fetchedSubjectDomainEntity);
    }

    private List<IssueDomainEntity> saveAllIssues(
            final Long subjectId,
            final List<String> issueKeyList
    ) {
        if (CollectionUtils.isEmpty(issueKeyList)) {
            return Collections.emptyList();
        }

        final var fetchedIssueDomainEntities = fetchAllIssuePort.fetchAllByIssueKeyList(
                issueKeyList);

        final var outCommand = CreateAllIssueOutCommand.builder()
                .subjectId(subjectId)
                .issueDomainEntities(fetchedIssueDomainEntities)
                .build();
        importParentEpicKey(outCommand.getIssueDomainEntities());


        return createAllIssuePort.saveAll(outCommand);
    }

    private void importParentEpicKey(List<IssueDomainEntity> issueDomainEntities){

        issueDomainEntities.forEach(this::setParentsEpicKey);
    }

    private void setParentsEpicKey(IssueDomainEntity issueDomainEntity) {
        if (issueDomainEntity.isSubtask()) {
            //Parent Task가 없는 경우가 존재하여 처리
            try {
                final var issue = issueRestClient.getIssue(issueDomainEntity.getParentTask()).get();
                final var issueField = issue.getField("customfield_10006");
                final var epicKey = ObjectUtils.defaultIfNull(issueField.getValue(), "").toString();
                issueDomainEntity.setEpicKey(epicKey);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
