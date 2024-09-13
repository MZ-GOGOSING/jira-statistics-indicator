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
 * Issue Comment Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_comment")
public class IssueCommentJpaEntity extends BaseJpaEntity {

  /**
   * 식별자.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * comment id.
   */
  @Column(name = "comment_id")
  private Long commentId;

  /**
   * 작성자 이름.
   */
  @Column(name = "author_display_name")
  private String authorDisplayName;

  /**
   * 작성자 아이디.
   */
  @Column(name = "author_account_id")
  private String authorAccountId;

  /**
   * 수정자 이름.
   */
  @Column(name = "update_author_display_name")
  private String updateAuthorDisplayName;

  /**
   * 수정자 아이디.
   */
  @Column(name = "update_author_account_id")
  private String updateAuthorAccountId;

  /**
   * comment 생성일.
   */
  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  /**
   * comment 최종 수정일.
   */
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  /**
   * comment 내용.
   */
  @Column(name = "body", columnDefinition = "TEXT")
  private String body;

  /**
   * 부모 Issue Entity.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "issue_id", referencedColumnName = "id")
  private IssueJpaEntity issue;
}
