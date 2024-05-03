package kr.co.mz.jira.jpa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IssueEndDateLogDto {
    //
    private Long issueId;
    private String issueKey;
    private String endDate;

    @QueryProjection
    public IssueEndDateLogDto(Long issueId, String issueKey, String endDate) {
        this.issueId = issueId;
        this.issueKey = issueKey;
        this.endDate = endDate;
    }
}
