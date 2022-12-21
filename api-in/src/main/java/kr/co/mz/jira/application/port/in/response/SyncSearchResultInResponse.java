package kr.co.mz.jira.application.port.in.response;

import java.util.List;
import java.util.Optional;
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
public class SyncSearchResultInResponse {

  private final String uuid;

  private final String jql;

  private final List<String> syncedIssueKeyList;

  private final String createdBy;

  public static SyncSearchResultInResponse of(
      final SubjectDomainEntity subjectDomainEntity,
      final List<IssueDomainEntity> issueDomainEntities
  ) {
    return Optional.ofNullable(subjectDomainEntity)
        .map(source -> SyncSearchResultInResponse.builder()
            .uuid(subjectDomainEntity.getUuid())
            .jql(subjectDomainEntity.getJql())
            .syncedIssueKeyList(
                CollectionUtils.emptyIfNull(issueDomainEntities)
                    .stream()
                    .map(IssueDomainEntity::getKey)
                    .collect(Collectors.toList())
            )
            .createdBy(subjectDomainEntity.getCreatedBy())
            .build())
        .orElse(null);
  }
}
