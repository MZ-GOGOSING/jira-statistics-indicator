package kr.co.mz.jira.code;

import kr.co.mz.support.code.DescriptionCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
public enum IssueStatus implements DescriptionCode {
    TO_DO("할일"),
    ANALYSIS("분석"),
    IN_DESIGN("설계 중"),
    IN_PROGRESS("진행 중"),
    IN_REVIEW("리뷰 중"),
    IN_TEST("테스트 중"),
    CONFIRMED("확인됨"),
    DONE("완료");

    private final String description;

    IssueStatus(final String description) {
        this.description = description;
    }

    public static IssueStatus of(final String code) {
        final var nullSafeCode = StringUtils.defaultString(code).toUpperCase();
        final var replacedCode = StringUtils.replace(nullSafeCode, " ", "_");

        try {
            return IssueStatus.valueOf(replacedCode);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.warn("입력된 코드는 존재하지 않는 유형 입니다. 추가 구현이 필요합니다.", illegalArgumentException);
            return null;
        }
    }
}
