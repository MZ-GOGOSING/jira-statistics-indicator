package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.IssueChangelogGroupJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueChangelogGroupJpaRepository extends JpaRepository<IssueChangelogGroupJpaEntity, Long> {

}
