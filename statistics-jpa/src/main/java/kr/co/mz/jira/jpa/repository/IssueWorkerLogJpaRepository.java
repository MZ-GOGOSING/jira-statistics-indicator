package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueWorkerLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueWorkerLogJpaRepository extends JpaRepository<IssueWorkerLogJpaEntity, Long> {
    //
}
