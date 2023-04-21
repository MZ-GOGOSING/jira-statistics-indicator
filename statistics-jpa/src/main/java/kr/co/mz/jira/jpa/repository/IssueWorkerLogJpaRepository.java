package kr.co.mz.jira.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.mz.jira.jpa.entity.IssueWorkerLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueWorkerLogJpaRepository extends JpaRepository<IssueWorkerLogJpaEntity, Long> {
    //
    List<IssueWorkerLogJpaEntity> findByWorkerAndWorkLogDateGreaterThanEqualAndWorkLogDateLessThanEqual(
            String worker, LocalDateTime startDate, LocalDateTime endDate);

    void deleteByWorkerAndWorkLogDateGreaterThanEqualAndWorkLogDateLessThanEqual(
            String worker, LocalDateTime startDate, LocalDateTime endDate);

    List<IssueWorkerLogJpaEntity> findByWorkerAndWorkLogDateGreaterThanEqualAndWorkLogDateLessThanEqualOrderByWorkLogDate(
        String worker, LocalDateTime startDate, LocalDateTime endDate);
}
