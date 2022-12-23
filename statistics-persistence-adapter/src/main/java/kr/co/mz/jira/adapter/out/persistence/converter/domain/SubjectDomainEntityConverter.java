package kr.co.mz.jira.adapter.out.persistence.converter.domain;

import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class SubjectDomainEntityConverter implements Converter<SubjectJpaEntity, SubjectDomainEntity> {

  @Override
  public SubjectDomainEntity convert(final SubjectJpaEntity subjectJpaEntity) {
    return SubjectDomainEntity.withId(
        subjectJpaEntity.getId(),
        subjectJpaEntity.getUuid(),
        subjectJpaEntity.getJql(),
        subjectJpaEntity.getJqlResult(),
        subjectJpaEntity.getCreatedBy(),
        subjectJpaEntity.getCreatedDate()
    );
  }
}
