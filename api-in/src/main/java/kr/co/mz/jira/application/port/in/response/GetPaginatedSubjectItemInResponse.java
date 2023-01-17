package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
public class GetPaginatedSubjectItemInResponse {

  private final Long id;

  private final String uuid;

  private final String jql;

  private final List<String> jqlResult;

  private final String createdBy;

  private final LocalDateTime createdDate;

  public static GetPaginatedSubjectItemInResponse of(final SubjectDomainEntity subjectDomainEntity) {
    return Optional.ofNullable(subjectDomainEntity)
        .map(source -> GetPaginatedSubjectItemInResponse.builder()
            .id(source.getId())
            .uuid(source.getUuid())
            .jql(source.getJql())
            .jqlResult(source.getIssueKeyList())
            .createdBy(source.getCreatedBy())
            .createdDate(source.getCreatedDate())
            .build())
        .orElse(null);
  }
}
