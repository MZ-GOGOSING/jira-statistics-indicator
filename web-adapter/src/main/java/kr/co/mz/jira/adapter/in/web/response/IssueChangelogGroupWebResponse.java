package kr.co.mz.jira.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.mz.jira.application.port.in.response.IssueChangelogGroupInResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

@Schema(description = "ISSUE CHANGE LOG (History) 정보 응답 모델")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueChangelogGroupWebResponse {

  @Schema(description = "작성자 계정", example = "mz_gogosing")
  private final String authorUsername;

  @Schema(description = "생성일", example = "2022-12-08 16:32:18.000000")
  private final LocalDateTime created;

  @Schema(description = "ISSUE CHANGE LOG (History) 항목 목록")
  private final List<IssueChangelogItemWebResponse> items;

  public static IssueChangelogGroupWebResponse of(
      final IssueChangelogGroupInResponse inResponse
  ) {
    return IssueChangelogGroupWebResponse.builder()
        .authorUsername(inResponse.getAuthorUsername())
        .created(inResponse.getCreated())
        .items(
            CollectionUtils.emptyIfNull(inResponse.getItems())
                .stream()
                .map(IssueChangelogItemWebResponse::of)
                .collect(Collectors.toList())
        )
        .build();
  }
}
