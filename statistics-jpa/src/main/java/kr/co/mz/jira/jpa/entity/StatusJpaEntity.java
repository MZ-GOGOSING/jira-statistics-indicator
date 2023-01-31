package kr.co.mz.jira.jpa.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Status Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "status")
public class StatusJpaEntity extends BaseJpaEntity {

  /**
   * 식별자.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * 동기화 수행일.
   */
  @Column(name = "sync_date", nullable = false)
  private LocalDate syncDate;

  /**
   * JIRA 시스템 상의 status 식별자.
   */
  @Column(name = "status_id", nullable = false)
  private Long statusId;

  /**
   * 설명.
   */
  @Column(name = "description", nullable = false)
  private String description;

  /**
   * icon 이미지 url.
   */
  @Column(name = "icon_url", nullable = false)
  private String iconUrl;

  /**
   * Status Category.
   */
  @OneToOne(
      mappedBy = "status",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
  )
  private StatusCategoryJpaEntity statusCategory;
}
