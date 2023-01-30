package kr.co.mz.jira.application.port.in.request.query;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import kr.co.mz.jira.code.IssueStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class GetSubjectDocumentInQuery {

  @NotBlank
  private final String uuid;

  @NotEmpty
  private final List<IssueStatus> workflow;
}
