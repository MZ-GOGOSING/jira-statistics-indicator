package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueChangelogGroupDomainEntityConverter implements Converter<ChangelogGroup, IssueChangelogGroupDomainEntity> {

  @NonNull
  @Override
  public IssueChangelogGroupDomainEntity convert(final @NotNull ChangelogGroup changelogGroup) {
    return null;
  }
}
