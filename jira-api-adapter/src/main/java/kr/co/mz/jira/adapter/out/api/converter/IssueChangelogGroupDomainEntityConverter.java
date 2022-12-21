package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueChangelogGroupDomainEntityConverter implements Converter<ChangelogGroup, IssueChangelogGroupDomainEntity> {

  @Override
  @SuppressWarnings("NullableProblems")
  public IssueChangelogGroupDomainEntity convert(final ChangelogGroup changelogGroup) {
    return null;
  }
}
