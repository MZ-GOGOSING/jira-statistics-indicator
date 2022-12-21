package kr.co.mz.jira.application.port.out.request.command;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.domain.IssueDomainEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CreateAllIssueOutCommand {

  @Min(1L)
  private final Long subjectId;

  @NotEmpty
  private final List<IssueDomainEntity> issueDomainEntities;
}
