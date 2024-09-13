package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import kr.co.mz.jira.domain.IssueCommentDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueCommentInResponse {

  private final String authorDisplayName;

  private final String authorAccountId;

  private final String updateAuthorDisplayName;

  private final String updateAuthorAccountId;

  private final LocalDateTime creationDate;

  private final LocalDateTime updateDate;

  private final String body;

  public static IssueCommentInResponse of(final IssueCommentDomainEntity issueCommentDomainEntity) {
    return IssueCommentInResponse.builder()
        .authorDisplayName(issueCommentDomainEntity.getAuthorDisplayName())
        .authorAccountId(issueCommentDomainEntity.getAuthorAccountId())
        .updateAuthorDisplayName(issueCommentDomainEntity.getUpdateAuthorDisplayName())
        .updateAuthorAccountId(issueCommentDomainEntity.getUpdateAuthorAccountId())
        .creationDate(issueCommentDomainEntity.getCreationDate())
        .updateDate(issueCommentDomainEntity.getUpdateDate())
        .body(issueCommentDomainEntity.getBody())
        .build();
  }
}
