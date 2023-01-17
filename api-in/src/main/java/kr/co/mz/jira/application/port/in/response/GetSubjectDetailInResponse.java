package kr.co.mz.jira.application.port.in.response;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.domain.SubjectDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetSubjectDetailInResponse {

  private final GetSubjectItemInResponse subjectItemInResponse;

  private final List<GetIssueItemInResponse> issueItemInResponseList;

  public static GetSubjectDetailInResponse of(
      final SubjectDomainEntity subjectDomainEntity,
      final List<IssueDomainEntity> issueDomainEntities
  ) {
    return GetSubjectDetailInResponse.builder()
        .subjectItemInResponse(GetSubjectItemInResponse.of(subjectDomainEntity))
        .issueItemInResponseList(
            CollectionUtils.emptyIfNull(issueDomainEntities)
                .stream()
                .map(GetIssueItemInResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
