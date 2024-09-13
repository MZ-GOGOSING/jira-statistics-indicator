package kr.co.mz.support.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageOptions {

  private int pageSize;

  private int pageNumber;

  @Getter(AccessLevel.NONE)
  public static final PageOptions DEFAULT = new PageOptions();

  public static PageOptions defaultOf(final int pageSize) {
    return PageOptions.builder()
        .pageSize(pageSize)
        .pageNumber(0)
        .build();
  }
}
