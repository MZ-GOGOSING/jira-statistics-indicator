package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueWorklogInResponse {

  private final String authorUsername;

  private final String updateAuthorUsername;

  private final String comment;

  private final LocalDateTime creationDate;

  private final LocalDateTime updateDate;

  private final LocalDateTime startDate;

  private final Integer minutesSpent;

  public static IssueWorklogInResponse of(final IssueWorklogDomainEntity issueWorklogDomainEntity) {
    return IssueWorklogInResponse.builder()
        .authorUsername(issueWorklogDomainEntity.getAuthorUsername())
        .updateAuthorUsername(issueWorklogDomainEntity.getUpdateAuthorUsername())
        .comment(issueWorklogDomainEntity.getComment())
        .creationDate(issueWorklogDomainEntity.getCreationDate())
        .updateDate(issueWorklogDomainEntity.getUpdateDate())
        .startDate(issueWorklogDomainEntity.getStartDate())
        .minutesSpent(issueWorklogDomainEntity.getMinutesSpent())
        .build();
  }
}
