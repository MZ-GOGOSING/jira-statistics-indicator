package kr.co.mz.jira.adapter.out.persistence.converter;

import java.util.Arrays;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import kr.co.mz.jira.jpa.entity.SubjectJpaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class SubjectDomainEntityConverter implements Converter<SubjectJpaEntity, SubjectDomainEntity> {

  private static final String ISSUE_KEY_SPLIT_REGEX = "\\s*,\\s*";

  @NonNull
  @Override
  public SubjectDomainEntity convert(final @NotNull SubjectJpaEntity subjectJpaEntity) {
    final var issueKeyList = Arrays.asList(
        StringUtils.defaultString(subjectJpaEntity.getJqlResult()).split(ISSUE_KEY_SPLIT_REGEX)
    );
    return SubjectDomainEntity.withId(
        subjectJpaEntity.getId(),
        subjectJpaEntity.getUuid(),
        subjectJpaEntity.getJql(),
        issueKeyList,
        subjectJpaEntity.getCreatedBy(),
        subjectJpaEntity.getCreatedDate()
    );
  }
}
