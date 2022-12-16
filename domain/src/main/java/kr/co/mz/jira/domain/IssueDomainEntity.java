package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDomainEntity {

  private final Long id;

  private final Long subjectId;

  private final Long externalId;

  private final String externalKey;

  private final String summary;

  private final String description;

  private final String status;

  private final String issueType;

  private final String reporter;

  private final String assignee;

  private final LocalDateTime creationDate;

  private final LocalDateTime updateDate;


}
