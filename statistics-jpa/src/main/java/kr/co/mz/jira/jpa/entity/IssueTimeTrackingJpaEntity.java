package kr.co.mz.jira.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Issue Time Tracking Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_time_tracking")
public class IssueTimeTrackingJpaEntity extends BaseJpaEntity {

  /**
   * 부모 Issue 식별자.
   */
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * Estimated Time.
   */
  @Column(name = "original_estimate_minutes")
  private Integer originalEstimateMinutes;

  /**
   * Remaining Time.
   */
  @Column(name = "remaining_estimate_minutes")
  private Integer remainingEstimateMinutes;

  /**
   * Logged Time.
   */
  @Column(name = "time_spent_minutes")
  private Integer timeSpentMinutes;

  /**
   * 부모 Issue Entity.
   */
  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private IssueJpaEntity issue;
}
