package kr.co.mz.jira.adapter.out.persistence;

import kr.co.mz.jira.adapter.out.persistence.converter.domain.SubjectDomainEntityConverter;
import kr.co.mz.jira.application.port.out.LoadSubjectPort;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.repository.SubjectJpaRepository;
import kr.co.mz.support.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoadSubjectPersistenceAdapter implements LoadSubjectPort {

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private final SubjectJpaRepository subjectJpaRepository;

  @Override
  public SubjectDomainEntity findById(final Long subjectId) {
    final var subjectDomainEntity = subjectJpaRepository.findById(subjectId)
        .orElseThrow(EntityNotFoundException::new);

    return SUBJECT_DOMAIN_ENTITY_CONVERTER.convert(subjectDomainEntity);
  }
}
