package kr.co.mz.jira.jpa.domain;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueStatusLogDomainEntity {
    //
    private LocalDateTime queryDate;
    private String epicKey;
    private Long issueId;
    private String issueKey;
    private IssueStatus issueStatus;
    private LocalDateTime toDoDate;
    private LocalDateTime analysisDate;
    private LocalDateTime inDesignDate;
    private LocalDateTime inProgressDate;
    private LocalDateTime inReviewDate;
    private LocalDateTime inTestDate;
    private LocalDateTime confirmedDate;
    private LocalDateTime doneDate;
    private LocalDateTime dueDate;
    private Long totalDelayedTime;
    private Set<String> labels;
    private String sprint;
    private String component;


    @Builder
    public IssueStatusLogDomainEntity(LocalDateTime queryDate, Long issueId, String issueKey,
            IssueStatus issueStatus, LocalDateTime toDoDate, LocalDateTime dueDate,
            Long totalDelayedTime, String sprint, String component) {
        this.queryDate = queryDate;
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.issueStatus = issueStatus;
        this.toDoDate = toDoDate;
        this.dueDate = dueDate;
        this.totalDelayedTime = totalDelayedTime;
        this.sprint = sprint;
        this.component = component;
    }

    // 시작 시간을 기준으로 하는 경우 여러 번의 동일한 상태변화에도 최초 한번만 업데이트 한다.
    public void setStatusDate(IssueStatus issueStatus, LocalDateTime statusDate) {
        //
        switch (issueStatus) {
            case ToDo:
                this.toDoDate = statusDate;
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
            case InTest:
                if(this.inTestDate == null) this.inTestDate = statusDate;
                break;
            case Confirmed:
                if(this.confirmedDate == null) this.confirmedDate = statusDate;
                break;
            case Done:
                if(this.doneDate == null) this.doneDate = statusDate;
                break;
        }
    }

    public void setStatusDateForItoDesign(IssueStatus issueStatus, LocalDateTime statusDate) {
        switch (issueStatus) {
            case ToDo:
                this.toDoDate = statusDate;
                break;
            case Preview:
                if(this.analysisDate == null) this.analysisDate = statusDate;
                break;
            case Doing:
                if(this.inProgressDate == null) this.inProgressDate = statusDate;
                break;
            case InReview:
                if(this.inReviewDate == null) this.inReviewDate = statusDate;
                break;
            case Done:
                if(this.doneDate == null) this.doneDate = statusDate;
                break;
        }
    }


    public void setStatusDateForInfra(IssueStatus issueStatus, LocalDateTime statusDate) {
        switch (issueStatus) {
            case ToDo:
                this.toDoDate = statusDate;
                break;
            case Analysis:
                if(this.analysisDate == null) this.analysisDate = statusDate;
                break;
            case Preview:
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
    
    public void resetStatusDate() {
        toDoDate = null;
        analysisDate = null;
        inDesignDate = null;
        inProgressDate = null;
        inReviewDate = null;
        inTestDate = null;
        confirmedDate = null;
        doneDate = null;
        totalDelayedTime = 0L;
    }
}
