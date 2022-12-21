package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogGroupDomainEntity {

  private final Long id;

  private final Long issueId;

  private final String authorDisplayName;

  private final LocalDateTime created;

  private IssueChangelogGroupDomainEntity(
      final Long id,
      final Long issueId,
      final String authorDisplayName,
      final LocalDateTime created
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    this.id = id;
    this.issueId = issueId;
    this.authorDisplayName = authorDisplayName;
    this.created = created;
  }

  public static IssueChangelogGroupDomainEntity withoutId(
      final Long issueId,
      final String authorDisplayName,
      final LocalDateTime created
  ) {
    return IssueChangelogGroupDomainEntity.builder()
        .issueId(issueId)
        .authorDisplayName(authorDisplayName)
        .created(created)
        .build();
  }

  public static IssueChangelogGroupDomainEntity withId(
      final Long id,
      final Long issueId,
      final String authorDisplayName,
      final LocalDateTime created
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");

    return IssueChangelogGroupDomainEntity.builder()
        .id(id)
        .issueId(issueId)
        .authorDisplayName(authorDisplayName)
        .created(created)
        .build();
  }
}
