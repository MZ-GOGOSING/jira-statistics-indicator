package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.Worklog;
import kr.co.mz.jira.domain.IssueWorklogDomainEntity;
import kr.co.mz.jira.support.converter.LocalDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class IssueWorklogDomainEntityConverter implements Converter<Worklog, IssueWorklogDomainEntity> {

  @Override
  public IssueWorklogDomainEntity convert(final Worklog worklog) {
    return IssueWorklogDomainEntity.fromOrigin(
        worklog.getAuthor().getName(),
        worklog.getUpdateAuthor().getName(),
        worklog.getComment(),
        LocalDateTimeConverter.fromJoda(worklog.getCreationDate()),
        LocalDateTimeConverter.fromJoda(worklog.getUpdateDate()),
        LocalDateTimeConverter.fromJoda(worklog.getStartDate()),
        worklog.getMinutesSpent()
    );
  }
}
