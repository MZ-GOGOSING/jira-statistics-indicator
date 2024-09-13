package kr.co.mz.jira.jpa.repository;

import java.util.List;
import java.util.Optional;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueJpaRepository extends
    JpaRepository<IssueJpaEntity, Long>,
    IssueJpaRepositoryCustom {

  @Override
  @NonNull
  @EntityGraph(
      attributePaths = {"timeTracking", "worklogs", "changelog", "comments", "changelog.items"},
      type = EntityGraphType.FETCH
  )
  Optional<IssueJpaEntity> findById(final @NonNull Long id);

  @EntityGraph(
      attributePaths = {"timeTracking", "worklogs", "changelog", "comments", "changelog.items"},
      type = EntityGraphType.FETCH
  )
  List<IssueJpaEntity> findAllBySubjectId(final Long subjectId);
}
