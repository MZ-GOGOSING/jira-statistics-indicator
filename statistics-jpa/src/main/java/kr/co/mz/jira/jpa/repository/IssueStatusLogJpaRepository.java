package kr.co.mz.jira.jpa.repository;

import java.util.List;
import kr.co.mz.jira.jpa.entity.IssueStatusLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueStatusLogJpaRepository extends JpaRepository<IssueStatusLogJpaEntity, Long> {
    //
    List<IssueStatusLogJpaEntity> findAllByIssueKeyIn(List<String> issueKeys);
}
