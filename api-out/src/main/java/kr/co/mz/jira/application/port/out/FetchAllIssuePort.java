package kr.co.mz.jira.application.port.out;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.domain.IssueDomainEntity;

public interface FetchAllIssuePort {

  List<IssueDomainEntity> fetchAllByIssueKeyList(final @NotEmpty List<String> issueKeyList);
}
