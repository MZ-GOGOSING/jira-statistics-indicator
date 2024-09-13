package kr.co.mz.jira.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChunkSize {

  P_50(50),
  P_100(100),
  P_500(500);

  private final int pageSize;
}
