package kr.co.mz.jira.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.persistence.converter.domain.IssueDomainEntityConverter;
import kr.co.mz.jira.adapter.out.persistence.converter.jpa.IssueJpaEntityConverter;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import kr.co.mz.jira.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional
public class CreateIssuePersistenceAdapter implements CreateAllIssuePort {

    private static final IssueJpaEntityConverter ISSUE_JPA_ENTITY_CONVERTER =
            new IssueJpaEntityConverter();

    private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
            new IssueDomainEntityConverter();

    private final IssueJpaRepository issueJpaRepository;
    private final IssueLogService issueLogService;

    @Override
    public List<IssueDomainEntity> saveAll(final CreateAllIssueOutCommand outCommand) {
        final var subjectId = outCommand.getSubjectId();
        final var issueJpaEntities = outCommand.getIssueDomainEntities()
                .stream()
                .map(issueDomainEntity -> this.saveIssueJpaEntity(subjectId, issueDomainEntity))
                .collect(Collectors.toList());

        return issueJpaEntities
                .stream()
                .map(ISSUE_DOMAIN_ENTITY_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void syncIssueLog(String uuid) {
        issueLogService.syncIssueLog(uuid);
    }

    @Override
    public void deleteIssueWorkerLog(String worker, String workDate) {
        issueLogService.deleteIssueWorkerLog(worker, workDate);
    }

    @Override
    public String selectWorkerLog(String worker, String workDate) {
        return issueLogService.selectIssueWorkerLog(worker, workDate);
    }

    private IssueJpaEntity saveIssueJpaEntity(
            final Long subjectId,
            final IssueDomainEntity issueDomainEntity
    ) {
        final var issueJpaEntity = ISSUE_JPA_ENTITY_CONVERTER.convert(
                subjectId,
                issueDomainEntity
        );

        if(issueJpaEntity.isSubTask()) {
            //Parent Task가 없는 경우가 존재하여 처리
            IssueJpaEntity parentEntity = ObjectUtils.defaultIfNull(issueJpaRepository.findBySubjectIdAndIssueKey(issueJpaEntity.getSubjectId(), issueDomainEntity.getParentTask()), new IssueJpaEntity());
            issueJpaEntity.setEpicKey(ObjectUtils.defaultIfNull(parentEntity.getEpicKey(), ""));
        }

        return issueJpaRepository.save(issueJpaEntity);
    }
}
