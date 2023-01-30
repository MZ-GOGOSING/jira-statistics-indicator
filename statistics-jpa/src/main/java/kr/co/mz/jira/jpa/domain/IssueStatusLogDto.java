package kr.co.mz.jira.jpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueStatusLogDto {
    //
    private Long issueId;
    private String issueKey;
    private Long changeLogGroupId;
    private String issueStatus;
    private LocalDateTime logDate;

    @QueryProjection
    public IssueStatusLogDto(Long issueId, String issueKey,
            Long changeLogGroupId,
            String issueStatus,
            LocalDateTime logDate) {
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.changeLogGroupId = changeLogGroupId;
        this.issueStatus = issueStatus;
        this.logDate = logDate;
    }
}
