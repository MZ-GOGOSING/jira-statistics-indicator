package kr.co.mz.jira.jpa.repository;

import java.time.LocalDate;
import java.util.List;
import kr.co.mz.jira.jpa.entity.StatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusJpaRepository extends JpaRepository<StatusJpaEntity, Long> {

  List<StatusJpaEntity> findAllBySyncDate(final LocalDate syncDate);
}
