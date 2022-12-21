package kr.co.mz.jira.domain;

import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDomainEntity {

  private final Long id;

  private final String key;

  private final Set<String> labels;

  private final TimeTrackingDomainEntity timeTrackingDomainEntity;


}
