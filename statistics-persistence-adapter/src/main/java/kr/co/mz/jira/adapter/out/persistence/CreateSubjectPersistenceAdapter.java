package kr.co.mz.jira.adapter.out.persistence;

import kr.co.mz.jira.adapter.out.persistence.converter.domain.SubjectDomainEntityConverter;
import kr.co.mz.jira.adapter.out.persistence.converter.jpa.SubjectJpaEntityConverter;
import kr.co.mz.jira.application.port.out.CreateSubjectPort;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.config.StatisticsJpaTransactional;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import kr.co.mz.jira.jpa.repository.SubjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@StatisticsJpaTransactional
public class CreateSubjectPersistenceAdapter implements CreateSubjectPort {

  private static final SubjectDomainEntityConverter SUBJECT_DOMAIN_ENTITY_CONVERTER =
      new SubjectDomainEntityConverter();

  private static final SubjectJpaEntityConverter SUBJECT_JPA_ENTITY_CONVERTER =
      new SubjectJpaEntityConverter();

  private final SubjectJpaRepository subjectJpaRepository;

  @Override
  public SubjectDomainEntity save(final SubjectDomainEntity outCommand) {
    final var storedSubjectJpaEntity = this.saveSubject(outCommand);

    return SUBJECT_DOMAIN_ENTITY_CONVERTER.convert(storedSubjectJpaEntity);
  }

  private SubjectJpaEntity saveSubject(final SubjectDomainEntity outCommand) {
    final var subjectJpaEntity = SUBJECT_JPA_ENTITY_CONVERTER.convert(outCommand);

    return subjectJpaRepository.save(subjectJpaEntity);
  }
}
