package kr.co.mz.jira.adapter.out.persistence.converter.jpa;

import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import org.springframework.core.convert.converter.Converter;

public class SubjectJpaEntityConverter implements Converter<SubjectDomainEntity, SubjectJpaEntity> {

  @Override
  public SubjectJpaEntity convert(final SubjectDomainEntity subjectDomainEntity) {
    return SubjectJpaEntity.builder()
        .id(subjectDomainEntity.getId())
        .uuid(subjectDomainEntity.getUuid())
        .jql(subjectDomainEntity.getJql())
        .jqlResult(subjectDomainEntity.getIssueKeyList())
        .createdBy(subjectDomainEntity.getCreatedBy())
        .createdDate(subjectDomainEntity.getCreatedDate())
        .build();
  }
}
