package kr.co.mz.jira.jpa.repository;

import java.util.List;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;

public interface IssueJpaRepositoryCustom {

  List<IssueJpaEntity> findAllBySubjectIdAndField(final Long subjectId, final String field);
}
