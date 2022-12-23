package kr.co.mz.jira.jpa.entity;

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
 * Issue Change Log Item Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_change_log_item")
public class IssueChangelogItemJpaEntity extends BaseJpaEntity {

  /**
   * 식별자.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * 부모 Issue Change Log Group Entity.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "change_log_group_id", referencedColumnName = "id")
  private IssueChangelogGroupJpaEntity issueChangelogGroup;

  /**
   * 필드명.
   */
  @Column(name = "field")
  private String field;

  /**
   * ~ 으로 부터.
   */
  @Column(name = "from_string", columnDefinition = "TEXT")
  private String fromString;

  /**
   * ~ 으로.
   */
  @Column(name = "to_string", columnDefinition = "TEXT")
  private String toString;
}
