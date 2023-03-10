package kr.co.mz.jira.adapter.out.persistence;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.adapter.out.persistence.converter.domain.IssueDomainEntityConverter;
import kr.co.mz.jira.application.port.out.LoadAllIssueItemPort;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.IssueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Component("loadAllDefaultIssueItemPort")
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoadAllDefaultIssueItemPersistenceAdapter implements LoadAllIssueItemPort {

  private static final IssueDomainEntityConverter ISSUE_DOMAIN_ENTITY_CONVERTER =
      new IssueDomainEntityConverter();

  private final IssueJpaRepository issueJpaRepository;

  @Override
  public List<IssueDomainEntity> findAllBySubjectId(final Long subjectId) {
    final var issueJpaEntities = issueJpaRepository.findAllBySubjectId(subjectId);

    return CollectionUtils.emptyIfNull(issueJpaEntities)
        .stream()
        .map(ISSUE_DOMAIN_ENTITY_CONVERTER::convert)
        .collect(Collectors.toList());
  }
}
