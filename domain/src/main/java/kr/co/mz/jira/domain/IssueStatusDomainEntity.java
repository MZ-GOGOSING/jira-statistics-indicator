package kr.co.mz.jira.domain;

import java.net.URI;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueStatusDomainEntity {

  private final Long id;

  private final String description;

  private final URI iconUrl;
}
