package kr.co.mz.jira.adapter.out.persistence;

import java.util.List;
import kr.co.mz.jira.application.port.out.CreateAllIssuePort;
import kr.co.mz.jira.application.port.out.request.command.CreateAllIssueOutCommand;
import kr.co.mz.jira.domain.IssueDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CreateIssuePersistenceAdapter implements CreateAllIssuePort {

  @Override
  public List<IssueDomainEntity> saveAll(final CreateAllIssueOutCommand outCommand) {
    return null;
  }
}
