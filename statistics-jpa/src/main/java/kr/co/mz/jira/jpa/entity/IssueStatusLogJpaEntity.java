package kr.co.mz.jira.jpa.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kr.co.mz.jira.jpa.converter.IssueLabelConverter;
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

    @Column(name = "query_date")
    private LocalDateTime queryDate;

    @Column(name = "epic_key")
    private String epicKey;

    @Column(name = "issue_id")
    private Long issueId;

    @Column(name = "issue_key")
    private String issueKey;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @Column(name = "to_do_date")
    private LocalDateTime toDoDate;

    @Column(name = "analysis_date")
    private LocalDateTime analysisDate;

    @Column(name = "in_design_date")
    private LocalDateTime inDesignDate;

    @Column(name = "in_progress_date")
    private LocalDateTime inProgressDate;

    @Column(name = "in_review_date")
    private LocalDateTime inReviewDate;

    @Column(name = "in_test_date")
    private LocalDateTime inTestDate;

    @Column(name = "confirmed_date")
    private LocalDateTime confirmedDate;

    @Column(name = "done_date")
    private LocalDateTime doneDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "sprint")
    private String sprint;

    @Builder.Default
    @Column(name = "labels", columnDefinition = "TEXT")
    @Convert(converter = IssueLabelConverter.class)
    private Set<String> labels = Collections.emptySet();

    public void fromDomain(IssueStatusLogDomainEntity domainEntity) {
        BeanUtils.copyProperties(domainEntity, this);
    }
}
