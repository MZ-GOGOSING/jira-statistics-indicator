package kr.co.mz.jira.api.request.command;

import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateIssueHeaderCommand {

  private final String projectKey;

  private final Long issueTypeId;

  public static CreateIssueHeaderCommand of(
      final String projectKey,
      final Long issueTypeId
  ) {
    Assert.hasText(projectKey, "Jira Issue 생성 시, projectKey 정보는 필수 입니다.");
    AssertHelper.isPositive(issueTypeId, "Jira Issue 생성 시, issueTypeId 정보는 필수 입니다.");

    return CreateIssueHeaderCommand.builder()
        .projectKey(projectKey)
        .issueTypeId(issueTypeId)
        .build();
  }
}
