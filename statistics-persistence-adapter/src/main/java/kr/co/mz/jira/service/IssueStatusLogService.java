package kr.co.mz.jira.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import kr.co.mz.jira.jpa.domain.IssueStatus;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDomainEntity;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDto;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import kr.co.mz.jira.jpa.repository.IssueStatusLogJpaRepository;
import kr.co.mz.jira.jpa.repository.SubjectJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueStatusLogService {
    //
    private final SubjectJpaRepository subjectJpaRepository;
    private final IssueJpaRepository issueJpaRepository;
    private final IssueStatusLogJpaRepository issueStatusLogJpaRepository;

    public void syncIssueStatusLog(String uuid) {
        //
        Optional<SubjectJpaEntity> optSubjectJpaEntity = subjectJpaRepository.findByUuid(uuid);
        if(optSubjectJpaEntity.isEmpty()) {
            log.info("Subject({}) does not exist.", uuid);
            return;
        }
        SubjectJpaEntity subjectJpaEntity = optSubjectJpaEntity.get();

        // 이슈 목록을 먼저 가져와서 생성해야 할 엔티티를 초기화 한다.
        List<IssueJpaEntity> issueJpaEntities
                = issueJpaRepository.findAllBySubjectId(subjectJpaEntity.getId());
        if(ObjectUtils.isEmpty(issueJpaEntities)) {
            log.info("No result issue.");
            return;
        }

        Map<String, IssueStatusLogDomainEntity> statusLogDomainEntityMap = new HashMap<>();
        issueJpaEntities.stream().forEach(
                entity ->
                        statusLogDomainEntityMap.put(
                            entity.getIssueKey(),
                            IssueStatusLogDomainEntity.builder()
                                    .queryDate(subjectJpaEntity.getCreatedDate())
                                    .issueId(entity.getId())
                                    .issueKey(entity.getIssueKey())
                                    .issueStatus(IssueStatus.getStatus(entity.getStatusName()))
                                    .toDoDate(entity.getCreationDate())
                                    .dueDate(entity.getDueDate())
                                    .build()
                    )
        );

        // 상태로그를 가져와서 entity를 업데이트 한다.
        List<IssueStatusLogDto> issueStatusLogDtos
            = issueJpaRepository.selectIssueStatusLog(subjectJpaEntity.getId());

        issueStatusLogDtos.stream().forEach(
                dto -> {
                    IssueStatusLogDomainEntity entity = statusLogDomainEntityMap.get(dto.getIssueKey());
                    entity.setStatusDate(dto.getIssueStatus(), dto.getLogDate());
                    statusLogDomainEntityMap.put(dto.getIssueKey(), entity);
                }
        );

        syncIssueStatusLog(statusLogDomainEntityMap);
    }

    private void syncIssueStatusLog(
            Map<String, IssueStatusLogDomainEntity> statusLogDomainEntityMap) {
        //
        List<String> issueKeys = new ArrayList<>(statusLogDomainEntityMap.keySet());
        List<IssueStatusLogJpaEntity> savedIssueStatusLogJpaEntities
                = issueStatusLogJpaRepository.findAllByIssueKeyIn(issueKeys);

        List<IssueStatusLogJpaEntity> savingIssueStatusLogJpaEntities = new ArrayList<>();

        savedIssueStatusLogJpaEntities.stream().forEach(
                entity -> {
                         entity.fromDomain(statusLogDomainEntityMap.remove(entity.getIssueKey()));
                         savingIssueStatusLogJpaEntities.add(entity);
                }
        );

        IssueStatusLogJpaEntity savingIssueStatusLogJpaEntity;
        for(IssueStatusLogDomainEntity domainEntity : statusLogDomainEntityMap.values()) {
            savingIssueStatusLogJpaEntity = new IssueStatusLogJpaEntity();
            BeanUtils.copyProperties(domainEntity, savingIssueStatusLogJpaEntity);
            savingIssueStatusLogJpaEntities.add(savingIssueStatusLogJpaEntity);
        }

        issueStatusLogJpaRepository.saveAll(savingIssueStatusLogJpaEntities);
    }

}
