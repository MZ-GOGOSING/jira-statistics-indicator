package kr.co.mz.jira.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChunkSize {

  C_5(5),
  C_10(10),
  C_25(25),
  C_50(50),
  C_100(100),
  C_500(500);

  private final int pageSize;
}
