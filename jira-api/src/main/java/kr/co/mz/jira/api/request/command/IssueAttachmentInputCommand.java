package kr.co.mz.jira.api.request.command;

import java.io.InputStream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class IssueAttachmentInputCommand {

  private final InputStream inputStream;

  private final String filename;

  private IssueAttachmentInputCommand(final InputStream inputStream, final String filename) {
    this.inputStream = inputStream;
    this.filename = filename;
  }

  public static IssueAttachmentInputCommand of(
      final InputStream inputStream,
      final String filename
  ) {
    Assert.notNull(inputStream, "첨부파일 정보는 필수 입니다.");
    Assert.hasText(filename, "첨부파일 이름은 필수 입니다.");

    return IssueAttachmentInputCommand.builder()
        .inputStream(inputStream)
        .filename(filename)
        .build();
  }
}
