package kr.co.mz.jira.api.request.command;

import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateIssueBodyCommand {

  private final String title;

  private final String contents;

  private final String assigneeUsername;

  private final String reporterUsername;

  private final Set<String> watcherUsernames;

  private final Set<IssueAttachmentInputCommand> attachments;

  public static CreateIssueBodyCommand of(
      final String title,
      final String contents,
      final String assigneeUsername,
      final String reporterUsername,
      final Set<String> watcherUsernames,
      final Set<IssueAttachmentInputCommand> attachments
  ) {
    Assert.hasText(title, "Jira Issue 생성 시, summary 정보는 필수 입니다.");
    Assert.hasText(contents, "Jira Issue 생성 시, Description 정보는 필수 입니다.");
    Assert.hasText(assigneeUsername, "Jira Issue 생성 시, Assignee 의 Id 는 필수 입니다.");
    Assert.hasText(reporterUsername, "Jira Issue 생성 시, Reporter 의 Id 는 필수 입니다.");

    return CreateIssueBodyCommand.builder()
        .title(title)
        .contents(contents)
        .assigneeUsername(assigneeUsername)
        .reporterUsername(reporterUsername)
        .watcherUsernames(watcherUsernames)
        .attachments(attachments)
        .build();
  }

  public static CreateIssueBodyCommand of(
      final String title,
      final String contents,
      final String assigneeUsername,
      final String reporterUsername,
      final Set<String> watcherUsernames
  ) {
    return CreateIssueBodyCommand.of(
        title,
        contents,
        assigneeUsername,
        reporterUsername,
        watcherUsernames,
        null
    );
  }
}
