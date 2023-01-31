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

  private final String statusName;

  private final Long statusId;

  private final String statusURI;

  private final String description;

  private final String iconUrl;

  private final StatusCategoryDomainEntity statusCategory;

  private StatusDomainEntity(
      final Long id,
      final LocalDate syncDate,
      final String statusName,
      final Long statusId,
      final String statusURI,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    AssertHelper.notNull(syncDate, "Status syncDate 는 null 일 수 없습니다.");
    AssertHelper.hasText(statusName, "Status Name 은 null 일 수 없습니다.");
    AssertHelper.isPositive(statusId, "Status id 는 0 이상의 수 이어야 합니다.");

    this.id = id;
    this.syncDate = syncDate;
    this.statusName = statusName;
    this.statusId = statusId;
    this.statusURI = statusURI;
    this.description = description;
    this.iconUrl = iconUrl;
    this.statusCategory = statusCategory;
  }

  public static StatusDomainEntity fromOrigin(
      final LocalDate syncDate,
      final String statusName,
      final Long statusId,
      final String statusURI,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    return StatusDomainEntity.builder()
        .syncDate(syncDate)
        .statusName(statusName)
        .statusId(statusId)
        .statusURI(statusURI)
        .description(description)
        .iconUrl(iconUrl)
        .statusCategory(statusCategory)
        .build();
  }

  public static StatusDomainEntity withId(
      final Long id,
      final LocalDate syncDate,
      final String statusName,
      final Long statusId,
      final String statusURI,
      final String description,
      final String iconUrl,
      final StatusCategoryDomainEntity statusCategory
  ) {
    AssertHelper.isPositive(id, "ID 는 0 이하 일 수 없습니다.");

    return StatusDomainEntity.builder()
        .id(id)
        .syncDate(syncDate)
        .statusName(statusName)
        .statusId(statusId)
        .statusURI(statusURI)
        .description(description)
        .iconUrl(iconUrl)
        .statusCategory(statusCategory)
        .build();
  }
}
