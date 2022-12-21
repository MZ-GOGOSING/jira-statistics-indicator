package kr.co.mz.jira.adapter.out.api.converter;

import com.atlassian.jira.rest.client.api.domain.ChangelogItem;
import javax.validation.constraints.NotNull;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class IssueChangelogItemDomainEntityConverter implements Converter<ChangelogItem, IssueChangelogItemDomainEntity> {

  @NonNull
  @Override
  public IssueChangelogItemDomainEntity convert(final @NotNull ChangelogItem changelogItem) {
    return null;
  }
}
