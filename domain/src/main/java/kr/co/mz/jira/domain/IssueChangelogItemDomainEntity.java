package kr.co.mz.jira.domain;

import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogItemDomainEntity {

  private final Long id;

  private final Long changelogGroupId;

  private final String field;

  private final String fromString;

  private final String toString;

  public static IssueChangelogItemDomainEntity fromOrigin(
      final String field,
      final String fromString,
      final String toString
  ) {
    return IssueChangelogItemDomainEntity.builder()
        .field(field)
        .fromString(fromString)
        .toString(toString)
        .build();
  }

  public static IssueChangelogItemDomainEntity withoutId(
      final Long changelogGroupId,
      final String field,
      final String fromString,
      final String toString
  ) {
    AssertHelper.isPositive(changelogGroupId, "부모 ChangelogGroup Id 는 0 이상의 수 이어야 합니다.");

    return IssueChangelogItemDomainEntity.builder()
        .changelogGroupId(changelogGroupId)
        .field(field)
        .fromString(fromString)
        .toString(toString)
        .build();
  }

  public static IssueChangelogItemDomainEntity withId(
      final Long id,
      final Long changelogGroupId,
      final String field,
      final String fromString,
      final String toString
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");
    AssertHelper.isPositive(changelogGroupId, "부모 ChangelogGroup Id 는 0 이상의 수 이어야 합니다.");

    return IssueChangelogItemDomainEntity.builder()
        .id(id)
        .changelogGroupId(changelogGroupId)
        .field(field)
        .fromString(fromString)
        .toString(toString)
        .build();
  }
}
