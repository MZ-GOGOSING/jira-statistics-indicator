package kr.co.mz.jira.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.mz.jira.jpa.domain.IssueStatus;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDomainEntity;
import kr.co.mz.jira.jpa.domain.IssueStatusLogDto;
import kr.co.mz.jira.jpa.domain.IssueWorkerLogDto;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueWorkerLogJpaEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import kr.co.mz.jira.jpa.repository.IssueStatusLogJpaRepository;
import kr.co.mz.jira.jpa.repository.IssueWorkerLogJpaRepository;
import kr.co.mz.jira.jpa.repository.IssueWorklogJpaRepository;
import kr.co.mz.jira.jpa.repository.SubjectJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueLogService {
    //
    private final SubjectJpaRepository subjectJpaRepository;
    private final IssueJpaRepository issueJpaRepository;
    private final IssueStatusLogJpaRepository issueStatusLogJpaRepository;
    private final IssueWorklogJpaRepository issueWorklogJpaRepository;
    private final IssueWorkerLogJpaRepository issueWorkerLogJpaRepository;

    public void syncIssueLog(String uuid) {
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

        Map<String, List<IssueStatusLogDomainEntity>> originStatusLogListMap = new HashMap<>();
        issueJpaEntities.forEach(
                entity -> {
                    List<IssueStatusLogDomainEntity> statusLogEntities = new ArrayList<>();
                    statusLogEntities.add(0,
                        IssueStatusLogDomainEntity.builder()
                                .queryDate(subjectJpaEntity.getCreatedDate())
                                .epicKey(entity.getEpicKey())
                                .issueId(entity.getId())
                                .issueKey(entity.getIssueKey())
                                .issueStatus(IssueStatus.getStatus(entity.getStatusName()))
                                .toDoDate(entity.getCreationDate())
                                .dueDate(entity.getDueDate())
                                .labels(entity.getLabels())
                                .build()
                    );
                    originStatusLogListMap.put(entity.getIssueKey(), statusLogEntities);
                }
        );

        //Map<String, IssueStatusLogDomainEntity> branchStatusLogMap = new HashMap<>();
        Map<String, IssueStatus> cursorStatusMap = new HashMap<>();
        // 상태로그를 가져와서 entity를 업데이트 한다.
        List<IssueStatusLogDto> issueStatusLogDtos
            = issueJpaRepository.selectIssueStatusLog(subjectJpaEntity.getId());

        issueStatusLogDtos.forEach(
                dto -> {
                    IssueStatus cursorStatus = cursorStatusMap.get(dto.getIssueKey());
                    IssueStatus issueStatus = IssueStatus.getStatus(dto.getIssueStatus());

                    List<IssueStatusLogDomainEntity> statusLogEntities
                            = originStatusLogListMap.get(dto.getIssueKey());
                    Integer maxIndex = statusLogEntities.size()-1;
                    IssueStatusLogDomainEntity entity = statusLogEntities.get(maxIndex);
                    if( cursorStatus != null && issueStatus.isBefore(cursorStatus) ) {
                        IssueStatusLogDomainEntity newEntity
                                = IssueStatusLogDomainEntity.builder().build();
                        BeanUtils.copyProperties(entity, newEntity);
                        newEntity.resetStatusDate();
                        newEntity.setStatusDate(issueStatus, dto.getLogDate());
                        statusLogEntities.add(newEntity);

                    } else {
                        entity.setStatusDate(issueStatus, dto.getLogDate());
                        statusLogEntities.set(maxIndex, entity);
                    }
                    originStatusLogListMap.put(dto.getIssueKey(), statusLogEntities);
                    cursorStatusMap.put(dto.getIssueKey(), issueStatus);
                }
        );

        List<IssueStatusLogDomainEntity> statusLogDomainEntities = new ArrayList<>();
        originStatusLogListMap.values().forEach(statusLogDomainEntities::addAll);

        syncIssueStatusLog(statusLogDomainEntities);
        synIssueWorkerLog(subjectJpaEntity.getId(), subjectJpaEntity.getCreatedDate());
    }

    private void syncIssueStatusLog(List<IssueStatusLogDomainEntity> statusLogDomainEntities) {
        //
        List<IssueStatusLogJpaEntity> savingIssueStatusLogJpaEntities = new ArrayList<>();

        IssueStatusLogJpaEntity savingIssueStatusLogJpaEntity;
        for(IssueStatusLogDomainEntity domainEntity : statusLogDomainEntities) {
            savingIssueStatusLogJpaEntity = new IssueStatusLogJpaEntity();
            BeanUtils.copyProperties(domainEntity, savingIssueStatusLogJpaEntity);
            savingIssueStatusLogJpaEntities.add(savingIssueStatusLogJpaEntity);
        }

        issueStatusLogJpaRepository.saveAll(savingIssueStatusLogJpaEntities);
    }

    private void synIssueWorkerLog(Long subjectId, LocalDateTime queryDate) {
        //
        List<IssueWorkerLogDto> issueWorkerLogDtos
                = issueWorklogJpaRepository.selectIssueWorkerLog(subjectId);
        if(ObjectUtils.isEmpty(issueWorkerLogDtos)) {
            log.info("No result issue worker log.");
            return;
        }
        List<IssueWorkerLogJpaEntity> issueWorkerLogJpaEntities = new ArrayList<>();
        IssueWorkerLogJpaEntity savingIssueWorkerLogJpaEntity;
        for(IssueWorkerLogDto dto : issueWorkerLogDtos) {
            savingIssueWorkerLogJpaEntity = new IssueWorkerLogJpaEntity();
            BeanUtils.copyProperties(dto, savingIssueWorkerLogJpaEntity);
            savingIssueWorkerLogJpaEntity.setQueryDate(queryDate);
            issueWorkerLogJpaEntities.add(savingIssueWorkerLogJpaEntity);
        }

        issueWorkerLogJpaRepository.saveAll(issueWorkerLogJpaEntities);
    }
}
