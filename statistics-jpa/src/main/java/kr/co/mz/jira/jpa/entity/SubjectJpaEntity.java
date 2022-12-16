package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
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
import org.springframework.data.annotation.CreatedDate;

/**
 * 통계 주제 Entity.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subject")
public class SubjectJpaEntity extends BaseJpaEntity {

  /**
   * 식별자.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  /**
   * UUID.
   */
  @Column(name = "uuid", nullable = false, unique = true)
  private String uuid;

  /**
   * JQL.(Jira Query Language)
   */
  @Column(name = "jql", nullable = false)
  private String jql;

  /**
   * 생성일시.
   */
  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private LocalDateTime createdDate;
}
