package kr.co.mz.jira.domain;

import kr.co.mz.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class StatusCategoryDomainEntity {

  private final Long statusId;

  private final String categoryURI;

  private final Long categoryId;

  private final String categoryKey;

  private final String categoryName;

  private final String categoryColorName;

  public static StatusCategoryDomainEntity fromOrigin(
      final Long categoryId,
      final String categoryURI,
      final String statusCategoryKey,
      final String categoryName,
      final String categoryColorName
  ) {
    return StatusCategoryDomainEntity.builder()
        .categoryId(categoryId)
        .categoryURI(categoryURI)
        .categoryKey(statusCategoryKey)
        .categoryName(categoryName)
        .categoryColorName(categoryColorName)
        .build();
  }

  public static StatusCategoryDomainEntity withId(
      final Long statusId,
      final String categoryURI,
      final Long categoryId,
      final String categoryKey,
      final String categoryName,
      final String categoryColorName
  ) {
    AssertHelper.isPositive(statusId, "부모 Status Id 는 0 이하 일 수 없습니다.");

    return StatusCategoryDomainEntity.builder()
        .statusId(statusId)
        .categoryURI(categoryURI)
        .categoryId(categoryId)
        .categoryKey(categoryKey)
        .categoryName(categoryName)
        .categoryColorName(categoryColorName)
        .build();
  }
}
