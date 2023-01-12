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

CREATE TABLE IF NOT EXISTS issue_status_log (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    query_date DATETIME NOT NULL COMMENT 'Jira 조회 시간',
    issue_id BIGINT(5) NOT NULL COMMENT 'Issue Id',
    issue_key VARCHAR(50) NOT NULL COMMENT 'Jira Issue Key',
    issue_status VARCHAR(10) NOT NULL COMMENT 'Jira 최종 상태',
    to_do_date DATETIME NOT NULL COMMENT '생성 시간',
    analysis_date DATETIME COMMENT '분석 시작 시간',
    in_design_date DATETIME COMMENT '설계 시작 시간',
    in_progress_date DATETIME COMMENT '개발 시작 시간',
    in_review_date DATETIME COMMENT '검토 시작 시간',
    confirmed_date DATETIME COMMENT '검토 완료 시간',
    done_date DATETIME COMMENT '티켓 처리 완료 시간',
    due_date DATETIME COMMENT ' 기한(처리목표시간)'
) COMMENT 'Issue Status Log';

CREATE TABLE IF NOT EXISTS issue_worker_log (
    id BIGINT(5) AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    query_date DATETIME NOT NULL COMMENT 'Jira 조회 시간',
    issue_id BIGINT(5) NOT NULL COMMENT 'Issue Id',
    issue_key VARCHAR(50) NOT NULL COMMENT 'Jira Issue Key',
    work_log_date DATETIME NOT NULL COMMENT '작업 착수 시간',
    worker VARCHAR(50) NOT NULL COMMENT '작업자',
    work_minute BIGINT(5) NOT NULL COMMENT '작업소요시간',
    work_comment VARCHAR(200) NOT NULL COMMENT '작업 코멘트'
) COMMENT 'Issue Worker Log';