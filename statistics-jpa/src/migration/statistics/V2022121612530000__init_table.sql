CREATE TABLE IF NOT EXISTS subject (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    uuid VARCHAR(36) NOT NULL COMMENT '주제 UUID',
    jql VARCHAR(1000) NOT NULL COMMENT 'JQL (Jira Query Language)',
    jql_result TEXT COMMENT 'JQL 검색결과(콤마 구분)',
    created_by VARCHAR(100) NOT NULL COMMENT '생성자',
    created_date DATETIME(6) NOT NULL DEFAULT current_timestamp COMMENT '생성일'
) COMMENT '통계 주제 테이블';

CREATE TABLE IF NOT EXISTS issue (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    subject_id BIGINT(5) NOT NULL COMMENT '통계 주제 식별자',
    issue_key VARCHAR(50) NOT NULL COMMENT 'Jira Issue Key',
    issue_uri VARCHAR(250) COMMENT 'Issue REST URI',
    watchers_uri VARCHAR(250) COMMENT 'Watchers REST URI',
    labels TEXT COMMENT 'Issue Labels. (Comma Delimiter)',
    due_date DATETIME(6) COMMENT '목표일',
    update_date DATETIME(6) COMMENT '최근 수정일',
    creation_date DATETIME(6) COMMENT '생성일',
    assignee_username VARCHAR(100) COMMENT 'Assignee 계정',
    reporter_username VARCHAR(100) COMMENT 'Reporter 계정',
    summary VARCHAR(1000) COMMENT '제목',
    description TEXT COMMENT '내용',
    issue_type_name VARCHAR(50) COMMENT 'Issue 유형',
    status_name VARCHAR(50) COMMENT 'Issue 상태'
) COMMENT 'Issue';

CREATE TABLE IF NOT EXISTS issue_time_tracking (
    id BIGINT(5) PRIMARY KEY COMMENT '부모 Issue 식별자',
    original_estimate_minutes INT COMMENT 'Estimated Time',
    remaining_estimate_minutes INT COMMENT 'Remaining Time',
    time_spent_minutes INT COMMENT 'Logged Time'
) COMMENT 'Issue Time Tracking';

CREATE TABLE IF NOT EXISTS issue_work_log (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    issue_id BIGINT(5) NOT NULL COMMENT '부모 Issue 식별자',
    author_username VARCHAR(100) COMMENT '작성자 계정',
    update_author_username VARCHAR(100) COMMENT '수정자 계정',
    comment TEXT COMMENT '댓글 내용',
    creation_date DATETIME(6) COMMENT '생성일',
    update_date DATETIME(6) COMMENT '최종 수정일',
    start_date DATETIME(6) COMMENT '시작일',
    minutes_spent INT COMMENT '작업 시간'
) COMMENT 'Issue Work Log';

CREATE TABLE IF NOT EXISTS issue_comment (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    issue_id BIGINT(5) NOT NULL COMMENT '부모 Issue 식별자',
    comment_id BIGINT(5) NOT NULL COMMENT 'comment 식별자',
    author_display_name VARCHAR(100) COMMENT '작성자 이름',
    author_account_id VARCHAR(100) COMMENT '작성자 아이디',
    update_author_display_name VARCHAR(100) COMMENT '수정자 이름',
    update_author_account_id VARCHAR(100) COMMENT '수정자 아이디',
    creation_date DATETIME(6) COMMENT '생성일',
    update_date DATETIME(6) COMMENT '최종 수정일',
    body TEXT COMMENT 'comment 내용'
) COMMENT 'Issue Comment';

CREATE TABLE IF NOT EXISTS issue_change_log_group (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    issue_id BIGINT(5) NOT NULL COMMENT '부모 Issue 식별자',
    author_username VARCHAR(100) COMMENT '작성자 계정',
    created DATETIME(6) COMMENT '생성일'
) COMMENT 'Issue Change Log Group';

CREATE TABLE IF NOT EXISTS issue_change_log_item (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    change_log_group_id BIGINT(5) NOT NULL COMMENT '부모 Issue Change Log Group 식별자',
    field VARCHAR(100) COMMENT '필드명',
    from_string TEXT COMMENT '~ 으로 부터',
    to_string TEXT COMMENT '~ 으로'
) COMMENT 'Issue Change Log Item';

CREATE TABLE IF NOT EXISTS status (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    sync_date DATE NOT NULL COMMENT '동기화 수행일',
    status_name VARCHAR(50) NOT NULL COMMENT 'Status 명칭',
    status_id BIGINT(5) NOT NULL COMMENT 'JIRA 시스템 상의 status 식별자',
    status_uri VARCHAR(250) COMMENT 'Status REST URI',
    description VARCHAR(100) COMMENT '설명',
    icon_url VARCHAR(250) COMMENT 'icon 이미지 url',
    CONSTRAINT subject_unique_index UNIQUE (sync_date, status_id)
) COMMENT 'Status';

CREATE TABLE IF NOT EXISTS status_category (
    id BIGINT(5) PRIMARY KEY COMMENT '부모 Status 식별자',
    category_id BIGINT(5) COMMENT 'JIRA 시스템 상의 status category 식별자',
    category_uri VARCHAR(250) COMMENT 'Status Category REST URI',
    category_name VARCHAR(50) NOT NULL COMMENT 'Category 명칭',
    category_key VARCHAR(50) COMMENT 'JIRA 시스템 상의 status category 대체 식별자',
    category_color_name VARCHAR(50) COMMENT '카테고리 색상'
) COMMENT 'Status Category';