package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueChangelogGroupDomainEntity;
import kr.co.mz.support.converter.LocalDateTimeConverter;
import kr.co.mz.support.converter.StreamConverter;
import org.springframework.core.convert.converter.Converter;

public class IssueChangelogGroupDomainEntityConverter implements Converter<ChangelogGroup, IssueChangelogGroupDomainEntity> {

  private static final IssueChangelogItemDomainEntityConverter ISSUE_CHANGELOG_ITEM_DOMAIN_ENTITY_CONVERTER =
      new IssueChangelogItemDomainEntityConverter();

  @Override
  public IssueChangelogGroupDomainEntity convert(final ChangelogGroup changelogGroup) {
    return IssueChangelogGroupDomainEntity.fromOrigin(
        changelogGroup.getAuthor().getName(),
        LocalDateTimeConverter.fromJoda(changelogGroup.getCreated()),
        StreamConverter
            .fromIterable(changelogGroup.getItems())
            .map(ISSUE_CHANGELOG_ITEM_DOMAIN_ENTITY_CONVERTER::convert)
            .collect(Collectors.toList())
    );
  }
}
