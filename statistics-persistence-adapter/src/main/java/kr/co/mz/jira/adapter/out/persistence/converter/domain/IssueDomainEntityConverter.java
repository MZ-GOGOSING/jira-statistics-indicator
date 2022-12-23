package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueDomainEntityConverter implements Converter<IssueJpaEntity, IssueDomainEntity> {

  @Override
  public IssueDomainEntity convert(final IssueJpaEntity issueJpaEntity) {
    return IssueDomainEntity.withId(
        issueJpaEntity.getId(),
        issueJpaEntity.getIssueKey(),
        issueJpaEntity.getIssueURI(),
        issueJpaEntity.getWatchersURI(),
        issueJpaEntity.getLabels(),
        issueJpaEntity.getDueDate(),
        issueJpaEntity.getUpdateDate(),
        issueJpaEntity.getCreationDate(),
        issueJpaEntity.getAssigneeUsername(),
        issueJpaEntity.getReporterUsername(),
        issueJpaEntity.getSummary(),
        issueJpaEntity.getIssueTypeName(),
        issueJpaEntity.getStatusName(),
        null,
        null,
        null
    );
  }
}
