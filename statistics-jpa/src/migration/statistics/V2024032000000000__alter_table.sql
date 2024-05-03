ALTER TABLE issue_worker_log modify COLUMN work_comment VARCHAR(500) NOT NULL;


alter table issue add column `end_date` datetime(6)  COMMENT '종료 일자'
