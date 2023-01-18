package kr.co.mz.jira.application.port.in.response;

import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogItemInResponse {

  private final String field;

  private final String fromString;

  private final String toString;

  public static IssueChangelogItemInResponse of(
      final IssueChangelogItemDomainEntity issueChangelogItemDomainEntity
  ) {
    return IssueChangelogItemInResponse.builder()
        .field(issueChangelogItemDomainEntity.getField())
        .fromString(issueChangelogItemDomainEntity.getFromString())
        .toString(issueChangelogItemDomainEntity.getToString())
        .build();
  }
}
