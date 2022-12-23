package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueTimeTrackingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueTimeTrackingJpaRepository extends JpaRepository<IssueTimeTrackingJpaEntity, Long> {

}
