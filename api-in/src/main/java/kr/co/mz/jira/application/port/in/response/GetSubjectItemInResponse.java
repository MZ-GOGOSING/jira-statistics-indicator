package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetSubjectItemInResponse {

  private final Long id;

  private final String uuid;

  private final String jql;

  private final List<String> jqlResult;

  private final String createdBy;

  private final LocalDateTime createdDate;

  public static GetSubjectItemInResponse of(final SubjectDomainEntity subjectDomainEntity) {
    return GetSubjectItemInResponse.builder()
        .id(subjectDomainEntity.getId())
        .uuid(subjectDomainEntity.getUuid())
        .jql(subjectDomainEntity.getJql())
        .jqlResult(subjectDomainEntity.getIssueKeyList())
        .createdBy(subjectDomainEntity.getCreatedBy())
        .createdDate(subjectDomainEntity.getCreatedDate())
        .build();
  }
}
