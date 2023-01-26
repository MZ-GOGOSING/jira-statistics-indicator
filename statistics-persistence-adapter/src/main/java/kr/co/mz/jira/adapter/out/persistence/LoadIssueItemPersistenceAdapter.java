package kr.co.mz.jira.adapter.out.persistence;

import kr.co.mz.jira.adapter.out.persistence.converter.domain.IssueDomainEntityConverter;
import kr.co.mz.jira.application.port.out.LoadIssueItemPort;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import kr.co.mz.support.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoadIssueItemPersistenceAdapter implements LoadIssueItemPort {

  private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
      new IssueDomainEntityConverter();

  private final IssueJpaRepository issueJpaRepository;

  @Override
  public IssueDomainEntity findById(final Long id) {
    final var issueJpaEntity = issueJpaRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);

    return ISSUE_DOMAIN_ENTITY_CONVERTER.convert(issueJpaEntity);
  }
}
