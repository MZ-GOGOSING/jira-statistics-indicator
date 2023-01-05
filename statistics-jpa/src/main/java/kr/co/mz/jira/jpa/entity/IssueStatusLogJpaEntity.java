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
import kr.co.mz.jira.jpa.domain.IssueStatusLogDomainEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "issue_status_log")
public class IssueStatusLogJpaEntity extends BaseJpaEntity {
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

    @Column(name = "issue_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @Column(name = "to_do_date", nullable = false)
    private LocalDateTime toDoDate;

    @Column(name = "analysis_date")
    private LocalDateTime analysisDate;

    @Column(name = "in_design_date")
    private LocalDateTime inDesignDate;

    @Column(name = "in_progress_date")
    private LocalDateTime inProgressDate;

    @Column(name = "in_review_date")
    private LocalDateTime inReviewDate;

    @Column(name = "confirmed_date")
    private LocalDateTime confirmedDate;

    @Column(name = "done_date")
    private LocalDateTime doneDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    public void fromDomain(IssueStatusLogDomainEntity domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }
}
