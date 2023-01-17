package kr.co.mz.jira.jpa.repository;

import java.util.List;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueJpaRepository extends JpaRepository<IssueJpaEntity, Long> {

  @EntityGraph(
      attributePaths = {"timeTracking", "worklogs", "changelog", "changelog.items"},
      type = EntityGraphType.FETCH
  )
  List<IssueJpaEntity> findAllBySubjectId(final Long subjectId);
}
