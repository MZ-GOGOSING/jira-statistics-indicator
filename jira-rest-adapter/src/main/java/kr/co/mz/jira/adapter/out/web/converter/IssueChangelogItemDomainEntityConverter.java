package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.ChangelogItem;
import kr.co.mz.jira.domain.IssueChangelogItemDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class IssueChangelogItemDomainEntityConverter implements Converter<ChangelogItem, IssueChangelogItemDomainEntity> {

  @Override
  public IssueChangelogItemDomainEntity convert(final ChangelogItem changelogItem) {
    return IssueChangelogItemDomainEntity.fromOrigin(
        changelogItem.getField(),
        changelogItem.getFromString(),
        changelogItem.getToString()
    );
  }
}
