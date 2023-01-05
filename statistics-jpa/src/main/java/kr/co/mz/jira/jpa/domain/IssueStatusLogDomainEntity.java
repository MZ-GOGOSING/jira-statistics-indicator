package kr.co.mz.jira.jpa.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueStatusLogDomainEntity {
    //
    private LocalDateTime queryDate;
    private Long issueId;
    private String issueKey;
    private IssueStatus issueStatus;
    private LocalDateTime toDoDate;
    private LocalDateTime analysisDate;
    private LocalDateTime inDesignDate;
    private LocalDateTime inProgressDate;
    private LocalDateTime inReviewDate;
    private LocalDateTime confirmedDate;
    private LocalDateTime doneDate;
    private LocalDateTime dueDate;

    @Builder
    public IssueStatusLogDomainEntity(LocalDateTime queryDate, Long issueId, String issueKey,
            IssueStatus issueStatus, LocalDateTime toDoDate, LocalDateTime dueDate) {
        this.queryDate = queryDate;
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.issueStatus = issueStatus;
        this.toDoDate = toDoDate;
        this.dueDate = dueDate;
    }

    // 시작 시간을 기준으로 하는 경우 여러 번의 동일한 상태변화에도 최초 한번만 업데이트 한다.
    public void setStatusDate(String status, LocalDateTime statusDate) {
        //
        IssueStatus issueStatus = IssueStatus.getStatus(status);
        switch (issueStatus) {
            case ToDo:
                if(this.toDoDate == null) this.toDoDate = statusDate;
                break;
            case Analysis:
                if(this.analysisDate == null) this.analysisDate = statusDate;
                break;
            case InDesign:
                if(this.inDesignDate == null) this.inDesignDate = statusDate;
                break;
            case InProgress:
                if(this.inProgressDate == null) this.inProgressDate = statusDate;
                break;
            case InReview:
                if(this.inReviewDate == null) this.inReviewDate = statusDate;
                break;
            case Confirmed:
                if(this.confirmedDate == null) this.confirmedDate = statusDate;
                break;
            case Done:
                if(this.doneDate == null) this.doneDate = statusDate;
                break;
        }
    }
}
