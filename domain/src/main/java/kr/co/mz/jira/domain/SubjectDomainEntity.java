package kr.co.mz.jira.domain;

import java.time.LocalDateTime;
import kr.co.mz.jira.support.assertion.AssertHelper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SubjectDomainEntity {

  private final Long id;

  private final String uuid;

  private final String jql;

  private final LocalDateTime createdDate;

  private SubjectDomainEntity(
      final Long id,
      final String uuid,
      final String jql,
      final LocalDateTime createdDate
  ) {
    AssertHelper.hasText(uuid, "UUID 는 빈 문자열일 수 없습니다.");
    AssertHelper.hasText(jql, "JQL 은 빈 문자열일 수 없습니다.");

    this.id = id;
    this.uuid = uuid;
    this.jql = jql;
    this.createdDate = createdDate;
  }

  public static SubjectDomainEntity withoutId(
      final String uuid,
      final String jql
  ) {
    return SubjectDomainEntity.builder()
        .uuid(uuid)
        .jql(jql)
        .build();
  }

  public static SubjectDomainEntity withId(
      final Long id,
      final String uuid,
      final String jql,
      final LocalDateTime createdDate
  ) {
    AssertHelper.isPositive(id, "ID 는 0 이하 일 수 없습니다.");
    AssertHelper.notNull(createdDate, "생성일시는 null 일 수 없습니다.");

    return SubjectDomainEntity.builder()
        .id(id)
        .uuid(uuid)
        .jql(jql)
        .createdDate(createdDate)
        .build();
  }
}
