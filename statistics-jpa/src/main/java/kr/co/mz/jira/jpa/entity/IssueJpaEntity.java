package kr.co.mz.jira.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Issue Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue")
public class IssueJpaEntity extends BaseJpaEntity {

  /**
   * 식별자. (Jira Issue의 고유 ID가 아닌 테이블 레코드의 고유 식별자)
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * 통계 주제 식별자.
   */
  @Column(name = "subject_id", nullable = false)
  private Long subjectId;

  /**
   * Jira Issue Key (e.g. ITO-80)
   */
  @Column(name = "issue_key", nullable = false)
  private String issueKey;

  /**
   * 총 Work Log. (Hour 단위)
   */
  @Column(name = "time_spent_minutes", nullable = false)
  private Integer timeSpentMinutes;
}
