package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kr.co.mz.jira.jpa.domain.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_worker_log")
public class IssueWorkerLogJpaEntity extends BaseJpaEntity {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "query_date", nullable = false)
    private LocalDateTime queryDate;

    @Column(name = "issue_id", nullable = false)
    private Long issueId;

    @Column(name = "issue_key", nullable = false)
    private String issueKey;

    @Column(name = "work_log_date", nullable = false)
    private LocalDateTime workLogDate;

    @Column(name = "worker", nullable = false)
    private String worker;

    @Column(name = "work_minute", nullable = false)
    private Long workMinute;
}
