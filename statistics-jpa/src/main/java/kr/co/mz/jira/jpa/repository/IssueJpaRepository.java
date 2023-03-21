package kr.co.mz.jira.jpa.repository;

import java.util.List;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueJpaRepository extends JpaRepository<IssueJpaEntity, Long>,
        IssueJpaRepositoryCustom {
    //
    List<IssueJpaEntity> findAllBySubjectId(Long subjectId);
    IssueJpaEntity findBySubjectIdAndIssueKey(Long subjectId, String issueKey);
}
