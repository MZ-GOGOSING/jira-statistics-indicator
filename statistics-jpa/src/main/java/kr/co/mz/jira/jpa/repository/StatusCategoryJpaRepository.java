package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.StatusCategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCategoryJpaRepository extends JpaRepository<StatusCategoryJpaEntity, Long> {

}
