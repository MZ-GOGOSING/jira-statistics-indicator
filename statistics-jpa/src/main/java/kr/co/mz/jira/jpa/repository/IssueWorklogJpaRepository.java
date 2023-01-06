package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueWorklogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueWorklogJpaRepository extends JpaRepository<IssueWorklogJpaEntity, Long>,
        IssueWorklogJpaRepositoryCustom {

}
