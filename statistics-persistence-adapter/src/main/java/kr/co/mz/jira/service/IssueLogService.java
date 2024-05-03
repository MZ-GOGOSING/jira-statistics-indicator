package kr.co.mz.jira.service;

import kr.co.mz.jira.jpa.domain.*;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import kr.co.mz.jira.jpa.entity.IssueWorkerLogJpaEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    private static final String ITO = "ITO";
    private static final String ITO_DESIGN = "ITODESIGN";
    private static final String ITO_IFROPS = "ITOIFROPS";


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
                                .dueDate(this.getDueDate(entity))
                                .labels(entity.getLabels())
                                .sprint(entity.getSprint())
                                .component(entity.getComponent())
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

                    // Delayed status인 경우에는 skip
                    if ( IssueStatus.Delayed.equals(issueStatus)) return;

                    List<IssueStatusLogDomainEntity> statusLogEntities
                            = originStatusLogListMap.get(dto.getIssueKey());
                    Integer maxIndex = statusLogEntities.size()-1;
                    IssueStatusLogDomainEntity entity = statusLogEntities.get(maxIndex);
                    // 이전 스탭의 상태값이 나오면 ReOpen으로 판단하여 새로운 row 생성
                    if( cursorStatus != null && issueStatus.isBefore(cursorStatus) ) {
                        IssueStatusLogDomainEntity newEntity = IssueStatusLogDomainEntity.builder().build();
                        BeanUtils.copyProperties(entity, newEntity);
                        newEntity.resetStatusDate();

                        String projectName = dto.getIssueKey().split("-")[0];

                        switch (projectName) {
                            case ITO:
                                newEntity.setStatusDate(issueStatus, dto.getLogDate());
                                break;
                            case ITO_DESIGN:
                                newEntity.setStatusDateForItoDesign(issueStatus, dto.getLogDate());
                                break;
                            case ITO_IFROPS:
                                newEntity.setStatusDateForInfra(issueStatus, dto.getLogDate());
                                break;
                            default:
                                break;
                        }
                        statusLogEntities.add(newEntity);

                    } else {
                        // Status에 매칭되는 issue_status_log 테이블 컬럼에 Date 저장
                        entity.setStatusDate(issueStatus, dto.getLogDate());
                        statusLogEntities.set(maxIndex, entity);
                    }
                    originStatusLogListMap.put(dto.getIssueKey(), statusLogEntities);
                    cursorStatusMap.put(dto.getIssueKey(), issueStatus);
                }
        );

        // delay time 로그를 가져와서 entity를 업데이트 한다
        List<IssueDelayedTimeLogDto> issueDelayedTimeLogDtos = issueJpaRepository.selectIssueDelayedTimeLog(subjectJpaEntity.getId());
        issueDelayedTimeLogDtos.forEach(
                dto -> {
                    List<IssueStatusLogDomainEntity> issueStatusLogDomainEntities = originStatusLogListMap.getOrDefault(dto.getIssueKey(), new ArrayList<>());
                    IssueStatusLogDomainEntity issueStatusLogDomainEntity = issueStatusLogDomainEntities.get(issueStatusLogDomainEntities.size() - 1);
                    if (ObjectUtils.isNotEmpty(issueStatusLogDomainEntity)) {
                        issueStatusLogDomainEntity.setTotalDelayedTime(Long.parseLong(dto.getTotalDelayedTime()));
                    }
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
            if (Objects.isNull(domainEntity.getTotalDelayedTime())) {
                domainEntity.setTotalDelayedTime(0L);
            }

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

    private LocalDateTime getDueDate(IssueJpaEntity entity) {
        return entity.getIssueKey().contains("ITODESIGN") ? entity.getEndDate() : entity.getDueDate();
    }
}
