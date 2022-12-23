package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import kr.co.mz.jira.jpa.converter.IssueLabelConverter;
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
   * Issue REST URI.
   */
  @Column(name = "issue_uri")
  private String issueURI;

  /**
   * Watchers REST URI.
   */
  @Column(name = "watchers_uri")
  private String watchersURI;

  /**
   * Issue Labels. (Comma Delimiter)
   */
  @Builder.Default
  @Column(name = "labels", columnDefinition = "TEXT")
  @Convert(converter = IssueLabelConverter.class)
  private Set<String> labels = Collections.emptySet();

  /**
   * 목표일.
   */
  @Column(name = "due_date")
  private LocalDateTime dueDate;

  /**
   * 최근 수정일.
   */
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  /**
   * 생성일.
   */
  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  /**
   * Assignee 계정.
   */
  @Column(name = "assignee_username")
  private String assigneeUsername;

  /**
   * Reporter 계정.
   */
  @Column(name = "reporter_username")
  private String reporterUsername;

  /**
   * 제목.
   */
  @Column(name = "summary")
  private String summary;

  /**
   * Issue 유형.
   */
  @Column(name = "issue_type_name")
  private String issueTypeName;

  /**
   * Issue 상태.
   */
  @Column(name = "status_name")
  private String statusName;

  /**
   * Issue Time Tracking.
   */
  @OneToOne(
      mappedBy = "issue",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
  )
  private IssueTimeTrackingJpaEntity timeTracking;

  /**
   * Issue Work Logs.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "issue",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      orphanRemoval = true
  )
  private Set<IssueWorklogJpaEntity> worklogs = new LinkedHashSet<>();

  /**
   * Issue Change Log Groups.
   */
  @Builder.Default
  @OneToMany(
      mappedBy = "issue",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
      orphanRemoval = true
  )
  private Set<IssueChangelogGroupJpaEntity> changelog = new LinkedHashSet<>();
}
