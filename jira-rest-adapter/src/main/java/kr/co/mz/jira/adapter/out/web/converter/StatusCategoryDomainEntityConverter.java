package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.StatusCategory;
import kr.co.mz.jira.domain.StatusCategoryDomainEntity;
import org.springframework.core.convert.converter.Converter;

public class StatusCategoryDomainEntityConverter implements Converter<StatusCategory, StatusCategoryDomainEntity> {

  @Override
  public StatusCategoryDomainEntity convert(final StatusCategory statusCategory) {
    return StatusCategoryDomainEntity.fromOrigin(
        statusCategory.getId(),
        statusCategory.getSelf().toString(),
        statusCategory.getKey(),
        statusCategory.getName(),
        statusCategory.getColorName()
    );
  }
}
