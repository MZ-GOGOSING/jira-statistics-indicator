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
 * Status Category Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "status_category")
public class StatusCategoryJpaEntity extends BaseJpaEntity {

  /**
   * 부모 Status 식별자.
   */
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * JIRA 시스템 상의 status category 식별자.
   */
  @Column(name = "category_id", nullable = false)
  private Long categoryId;

  /**
   * JIRA 시스템 상의 status category 대체 식별자.
   */
  @Column(name = "category_key", nullable = false)
  private String categoryKey;

  /**
   * 카테고리 색상.
   */
  @Column(name = "category_color_name", nullable = false)
  private String categoryColorName;

  /**
   * 부모 Status Entity.
   */
  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private StatusJpaEntity status;
}
