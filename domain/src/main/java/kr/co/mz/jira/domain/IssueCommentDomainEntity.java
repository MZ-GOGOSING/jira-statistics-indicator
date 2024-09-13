package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import kr.co.mz.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueCommentDomainEntity {

  private final Long id;

  private final Long issueId;

  private final Long commentId;

  private final String authorDisplayName;

  private final String authorAccountId;

  private final String updateAuthorDisplayName;

  private final String updateAuthorAccountId;

  private final LocalDateTime creationDate;

  private final LocalDateTime updateDate;

  private final String body;

  public static IssueCommentDomainEntity fromOrigin(
      final Long commentId,
      final String authorDisplayName,
      final String authorAccountId,
      final String updateAuthorDisplayName,
      final String updateAuthorAccountId,
      final LocalDateTime creationDate,
      final LocalDateTime updateDate,
      final String body
  ) {
    return IssueCommentDomainEntity.builder()
        .commentId(commentId)
        .authorDisplayName(authorDisplayName)
        .authorAccountId(authorAccountId)
        .updateAuthorDisplayName(updateAuthorDisplayName)
        .updateAuthorAccountId(updateAuthorAccountId)
        .creationDate(creationDate)
        .updateDate(updateDate)
        .body(body)
        .build();
  }

  public static IssueCommentDomainEntity withId(
      final Long id,
      final Long issueId,
      final Long commentId,
      final String authorDisplayName,
      final String authorAccountId,
      final String updateAuthorDisplayName,
      final String updateAuthorAccountId,
      final LocalDateTime creationDate,
      final LocalDateTime updateDate,
      final String body
  ) {
    AssertHelper.isPositive(id, "Id 는 0 이상의 수 이어야 합니다.");
    AssertHelper.isPositive(issueId, "부모 Issue Id 는 0 이상의 수 이어야 합니다.");

    return IssueCommentDomainEntity.builder()
        .id(id)
        .issueId(issueId)
        .commentId(commentId)
        .authorDisplayName(authorDisplayName)
        .authorAccountId(authorAccountId)
        .updateAuthorDisplayName(updateAuthorDisplayName)
        .updateAuthorAccountId(updateAuthorAccountId)
        .creationDate(creationDate)
        .updateDate(updateDate)
        .body(body)
        .build();
  }
}
