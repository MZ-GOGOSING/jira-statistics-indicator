package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Issue Work Log Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_work_log")
public class IssueWorklogJpaEntity extends BaseJpaEntity {

  /**
   * 식별자.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * 부모 Issue Entity.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "issue_id", referencedColumnName = "id")
  private IssueJpaEntity issue;

  /**
   * 작성자 계정.
   */
  @Column(name = "author_username")
  private String authorUsername;

  /**
   * 수정자 계정.
   */
  @Column(name = "update_author_username")
  private String updateAuthorUsername;

  /**
   * 댓글 내용.
   */
  @Column(name = "comment", columnDefinition = "TEXT")
  private String comment;

  /**
   * 생성일.
   */
  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  /**
   * 최종 수정일.
   */
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  /**
   * 시작일.
   */
  @Column(name = "start_date")
  private LocalDateTime startDate;

  /**
   * 작업 시간.
   */
  @Column(name = "minutes_spent")
  private Integer minutesSpent;
}
