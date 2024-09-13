package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueCommentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueCommentJpaRepository extends JpaRepository<IssueCommentJpaEntity, Long> {

}
