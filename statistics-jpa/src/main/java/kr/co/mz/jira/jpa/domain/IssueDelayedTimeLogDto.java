package kr.co.mz.jira.jpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueDelayedTimeLogDto {
    //
    private Long issueId;
    private String issueKey;
    private String totalDelayedTime;

    @QueryProjection
    public IssueDelayedTimeLogDto(Long issueId, String issueKey, String totalDelayedTime) {
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.totalDelayedTime = totalDelayedTime;
    }
}
