alter table issue add column `component` varchar(20)  default '' COMMENT '구성 요소'

alter table issue_status_log add column `component` varchar(20) default '' COMMENT '구성 요소'