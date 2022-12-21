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
public class IssueWorklogDomainEntity {

  private final Long id;

  private final Long issueId;

  private final String authorUsername;

  private final String updateAuthorUsername;

  private final String comment;

  private final LocalDateTime creationDate;

  private final LocalDateTime updateDate;

  private final LocalDateTime startDate;

  private final Integer minutesSpent;

  private IssueWorklogDomainEntity(
      final Long id,
      final Long issueId,
      final String authorUsername,
      final String updateAuthorUsername,
      final String comment,
      final LocalDateTime creationDate,
      final LocalDateTime updateDate,
      final LocalDateTime startDate,
      final Integer minutesSpent
  ) {
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    this.id = id;
    this.issueId = issueId;
    this.authorUsername = authorUsername;
    this.updateAuthorUsername = updateAuthorUsername;
    this.comment = comment;
    this.creationDate = creationDate;
    this.updateDate = updateDate;
    this.startDate = startDate;
    this.minutesSpent = minutesSpent;
  }

  public static IssueWorklogDomainEntity withoutId(
      final Long issueId,
      final String authorUsername,
      final String updateAuthorUsername,
      final String comment,
      final LocalDateTime creationDate,
      final LocalDateTime updateDate,
      final LocalDateTime startDate,
      final Integer minutesSpent
  ) {
    return IssueWorklogDomainEntity.builder()
        .issueId(issueId)
        .authorUsername(authorUsername)
        .updateAuthorUsername(updateAuthorUsername)
        .comment(comment)
        .creationDate(creationDate)
        .updateDate(updateDate)
        .startDate(startDate)
        .minutesSpent(minutesSpent)
        .build();
  }

  public static IssueWorklogDomainEntity withId(
      final Long id,
      final Long issueId,
      final String authorUsername,
      final String updateAuthorUsername,
      final String comment,
      final LocalDateTime creationDate,
      final LocalDateTime updateDate,
      final LocalDateTime startDate,
      final Integer minutesSpent
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");

    return IssueWorklogDomainEntity.builder()
        .id(id)
        .issueId(issueId)
        .authorUsername(authorUsername)
        .updateAuthorUsername(updateAuthorUsername)
        .comment(comment)
        .creationDate(creationDate)
        .updateDate(updateDate)
        .startDate(startDate)
        .minutesSpent(minutesSpent)
        .build();
  }
}
