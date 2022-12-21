-- https://stackoverflow.com/questions/56831529/configuring-a-xa-datasource-to-mysql-8-db-with-spring-boot-and-bitronix-jta-mana
GRANT XA_RECOVER_ADMIN ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS subject (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    uuid VARCHAR(36) NOT NULL COMMENT '주제 UUID',
    jql VARCHAR(1000) NOT NULL COMMENT 'JQL (Jira Query Language)',
    jql_result TEXT COMMENT 'JQL 검색결과(콤마 구분)',
    created_by VARCHAR(100) NOT NULL COMMENT '생성자',
    created_date DATETIME NOT NULL DEFAULT current_timestamp COMMENT '생성일'
) COMMENT '통계 주제 테이블';

CREATE TABLE IF NOT EXISTS issue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    subject_id BIGINT NOT NULL COMMENT '통계 주제 식별자',
    issue_key VARCHAR(50) NOT NULL COMMENT 'Jira Issue Key',
    time_spent_minutes INT NOT NULL DEFAULT 0 COMMENT '총 Work Log (Hour 단위)'
) COMMENT 'Issue';