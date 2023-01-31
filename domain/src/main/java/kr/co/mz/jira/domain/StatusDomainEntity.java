package kr.co.mz.jira.domain;

import java.time.LocalDate;
import kr.co.mz.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class StatusDomainEntity {

  private final Long id;

  private final LocalDate syncDate;

  private final Long statusId;

  private final String description;

  private final String iconUrl;

  private final StatusCategoryDomainEntity statusCategory;

  private StatusDomainEntity(
      final Long id,
      final LocalDate syncDate,
      final Long statusId,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    AssertHelper.isPositive(statusId, "Status id 는 0 이상의 수 이어야 합니다.");
    AssertHelper.notNull(syncDate, "Status syncDate 는 null 일 수 없습니다.");
    AssertHelper.hasText(description, "Status description 은 빈 문자열일 수 없습니다.");

    this.id = id;
    this.syncDate = syncDate;
    this.statusId = statusId;
    this.description = description;
    this.iconUrl = iconUrl;
    this.statusCategory = statusCategory;
  }

  public static StatusDomainEntity fromOrigin(
      final LocalDate syncDate,
      final Long statusId,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    return StatusDomainEntity.builder()
        .syncDate(syncDate)
        .statusId(statusId)
        .description(description)
        .iconUrl(iconUrl)
        .statusCategory(statusCategory)
        .build();
  }

  public static StatusDomainEntity withId(
      final Long id,
      final LocalDate syncDate,
      final Long statusId,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    AssertHelper.isPositive(id, "ID 는 0 이하 일 수 없습니다.");

    return StatusDomainEntity.builder()
        .id(id)
        .syncDate(syncDate)
        .statusId(statusId)
        .description(description)
        .iconUrl(iconUrl)
        .statusCategory(statusCategory)
        .build();
  }
}
