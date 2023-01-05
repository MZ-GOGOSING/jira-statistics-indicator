package kr.co.mz.jira.jpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueWorkerLogDto {
    //
    private Long issueId;
    private String issueKey;
    private LocalDateTime workLogDate;
    private String worker;
    private Long workMinute;

    @QueryProjection
    public IssueWorkerLogDto(Long issueId, String issueKey,
            LocalDateTime workLogDate, String worker, Long workMinute) {
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.workLogDate = workLogDate;
        this.worker = worker;
        this.workMinute = workMinute;
    }
}
