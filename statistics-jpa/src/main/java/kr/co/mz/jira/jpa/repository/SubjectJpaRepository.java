package kr.co.mz.jira.jpa.repository;

import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectJpaRepository extends JpaRepository<SubjectJpaEntity, Long> {

}
