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
public class CreateSubjectPersistenceAdapter implements CreateSubjectPort {

  private final SubjectJpaRepository subjectJpaRepository;

  @Override
  @StatisticsJpaTransactional
  public SubjectDomainEntity save(final SubjectDomainEntity outCommand) {
    final var storedSubjectJpaEntity = this.saveSubject(outCommand);

    return new SubjectDomainEntityConverter().convert(storedSubjectJpaEntity);
  }

  private SubjectJpaEntity saveSubject(final SubjectDomainEntity outCommand) {
    final var generatedSubjectJpaEntity = new SubjectJpaEntityConverter().convert(outCommand);

    return subjectJpaRepository.save(generatedSubjectJpaEntity);
  }
}
