package kr.co.mz.jira.api.request.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateIssueCommand {

  private final CreateIssueHeaderCommand header;

  private final CreateIssueBodyCommand body;

  public static CreateIssueCommand of(
      final CreateIssueHeaderCommand header,
      final CreateIssueBodyCommand body
  ) {
    Assert.notNull(header, "Jira Issue 생성 시, header 정보는 필수 입니다.");
    Assert.notNull(body, "Jira Issue 생성 시, body 정보는 필수 입니다.");

    return CreateIssueCommand.builder()
        .header(header)
        .body(body)
        .build();
  }
}
