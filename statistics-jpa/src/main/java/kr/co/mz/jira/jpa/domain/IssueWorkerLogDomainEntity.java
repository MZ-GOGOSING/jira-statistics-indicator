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
public class IssueWorkerLogDomainEntity {
    //
    private Long id;
    private LocalDateTime queryDate;
    private Long issueId;
    private String issueKey;
    private LocalDateTime workLogDate;
    private String worker;
    private Long workMinute;
    private String workComment;
}
