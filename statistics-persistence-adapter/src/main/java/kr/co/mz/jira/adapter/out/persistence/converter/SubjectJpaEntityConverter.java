package kr.co.mz.jira.adapter.out.persistence.converter;

import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class SubjectJpaEntityConverter implements Converter<SubjectDomainEntity, SubjectJpaEntity> {

  private static final String ISSUE_KEY_DELIMITER_CHAR = ",";

  @NonNull
  @Override
  public SubjectJpaEntity convert(final @NotNull SubjectDomainEntity subjectDomainEntity) {
    final var jqlResult = String.join(
        ISSUE_KEY_DELIMITER_CHAR,
        CollectionUtils.emptyIfNull(subjectDomainEntity.getIssueKeyList())
    );
    return SubjectJpaEntity.builder()
        .id(subjectDomainEntity.getId())
        .uuid(subjectDomainEntity.getUuid())
        .jql(subjectDomainEntity.getJql())
        .jqlResult(jqlResult)
        .createdBy(subjectDomainEntity.getCreatedBy())
        .createdDate(subjectDomainEntity.getCreatedDate())
        .build();
  }
}
