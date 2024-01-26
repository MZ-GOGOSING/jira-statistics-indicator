package kr.co.mz.jira.adapter.in.web.request;

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
public class SyncSearchWorkLogWebCommand {

  @NotBlank
  @Schema(
      description = "우형 계정 아이디",
      example = "mz_kspark",
      required = true
  )
  private String worker;
  private String workDate;
  private String project;
}
