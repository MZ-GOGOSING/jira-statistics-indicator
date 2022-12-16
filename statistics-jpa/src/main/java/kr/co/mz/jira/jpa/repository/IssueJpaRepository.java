package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueJpaRepository extends JpaRepository<IssueJpaEntity, Long> {

}
