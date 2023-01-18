package kr.co.mz.jira.application.port.in.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogGroupInResponse {

  private final String authorUsername;

  private final LocalDateTime created;

  private final List<IssueChangelogItemInResponse> items;

  public static IssueChangelogGroupInResponse of(
      final IssueChangelogGroupDomainEntity issueChangelogGroupDomainEntity
  ) {
    return IssueChangelogGroupInResponse.builder()
        .authorUsername(issueChangelogGroupDomainEntity.getAuthorUsername())
        .created(issueChangelogGroupDomainEntity.getCreated())
        .items(
            CollectionUtils.emptyIfNull(issueChangelogGroupDomainEntity.getItems())
                .stream()
                .map(IssueChangelogItemInResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
