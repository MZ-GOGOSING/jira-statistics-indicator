package kr.co.mz.jira.jpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueWorkerLogDto {
    //
    private Long issueId;
    private String issueKey;
    private LocalDateTime workLogDate;
    private String worker;
    private Long workMinute;
    private String workComment;

    @QueryProjection
    public IssueWorkerLogDto(Long issueId, String issueKey,
            LocalDateTime workLogDate, String worker, Integer workMinute,
            String workComment) {
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.workLogDate = workLogDate;
        this.worker = worker;
        this.workMinute =  workMinute.longValue();
        this.workComment = workComment;
    }
}
