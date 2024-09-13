package kr.co.mz.jira.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.persistence.converter.domain.IssueDomainEntityConverter;
import kr.co.mz.jira.adapter.out.persistence.converter.jpa.IssueJpaEntityConverter;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.entity.IssueJpaEntity;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.REQUIRES_NEW)
public class CreateAllIssuePersistenceAdapter implements CreateAllIssuePort {

  private static final IssueJpaEntityConverter ISSUE_JPA_ENTITY_CONVERTER =
      new IssueJpaEntityConverter();

  private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
      new IssueDomainEntityConverter();

  private final IssueJpaRepository issueJpaRepository;

  @Override
  public List<IssueDomainEntity> saveAll(final CreateAllIssueOutCommand outCommand) {
    final var subjectId = outCommand.getSubjectId();
    final var issueJpaEntities = outCommand.getIssueDomainEntities()
        .stream()
        .map(issueDomainEntity -> this.saveIssueJpaEntity(subjectId, issueDomainEntity))
        .collect(Collectors.toList());

    return issueJpaEntities
        .stream()
        .map(ISSUE_DOMAIN_ENTITY_CONVERTER::convert)
        .collect(Collectors.toList());
  }

  private IssueJpaEntity saveIssueJpaEntity(
      final Long subjectId,
      final IssueDomainEntity issueDomainEntity
  ) {
    final var issueJpaEntity = ISSUE_JPA_ENTITY_CONVERTER.convert(
        subjectId,
        issueDomainEntity
    );
    return issueJpaRepository.save(issueJpaEntity);
  }
}
