package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Issue Change Log Group Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_change_log_group")
public class IssueChangelogGroupJpaEntity extends BaseJpaEntity {

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
   * 생성일.
   */
  @Column(name = "created")
  private LocalDateTime created;

  /**
   * Issue Change Log Items.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "issueChangelogGroup",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      orphanRemoval = true
  )
  private Set<IssueChangelogItemJpaEntity> items = new LinkedHashSet<>();
}
