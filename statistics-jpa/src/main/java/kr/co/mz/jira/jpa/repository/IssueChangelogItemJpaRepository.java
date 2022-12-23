package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueChangelogItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueChangelogItemJpaRepository extends JpaRepository<IssueChangelogItemJpaEntity, Long> {

}
