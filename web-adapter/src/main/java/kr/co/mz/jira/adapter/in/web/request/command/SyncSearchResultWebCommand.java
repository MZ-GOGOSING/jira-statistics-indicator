package kr.co.mz.jira.adapter.in.web.request.command;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Issue 검색 및 동기화 모델")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SyncSearchResultWebCommand {

  @NotBlank
  @Schema(
      description = "검색 JQL",
      example = "project = ITO AND worklogDate >= 2022-12-01 AND worklogDate <= 2022-12-31",
      required = true
  )
  private String jql;
}
