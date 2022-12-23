package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import java.util.List;
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
public class IssueChangelogGroupDomainEntity {

  private final Long id;

  private final Long issueId;

  private final String authorUsername;

  private final LocalDateTime created;

  private final List<IssueChangelogItemDomainEntity> items;

  public static IssueChangelogGroupDomainEntity fromOrigin(
      final String authorUsername,
      final LocalDateTime created,
      final List<IssueChangelogItemDomainEntity> items
  ) {
    return IssueChangelogGroupDomainEntity.builder()
        .authorUsername(authorUsername)
        .created(created)
        .items(items)
        .build();
  }

  public static IssueChangelogGroupDomainEntity withoutId(
      final Long issueId,
      final String authorUsername,
      final LocalDateTime created,
      final List<IssueChangelogItemDomainEntity> items
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    return IssueChangelogGroupDomainEntity.builder()
        .issueId(issueId)
        .authorUsername(authorUsername)
        .created(created)
        .items(items)
        .build();
  }

  public static IssueChangelogGroupDomainEntity withId(
      final Long id,
      final Long issueId,
      final String authorUsername,
      final LocalDateTime created,
      final List<IssueChangelogItemDomainEntity> items
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    return IssueChangelogGroupDomainEntity.builder()
        .id(id)
        .issueId(issueId)
        .authorUsername(authorUsername)
        .created(created)
        .items(items)
        .build();
  }
}
