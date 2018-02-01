create table kxl_message_state
(
	message_id bigint not null,
	id int auto_increment
		primary key,
	send_user varchar(36) not null comment '发送者ID',
	receive_user varchar(36) not null comment '接受用户ID',
	message_state int not null comment '消息状态：0-未读，1-已读',
	is_delete int not null comment '消息是否已经删除',
	message_type int not null comment '消息类型',
	create_time datetime null comment '消息创建时间',
	update_time datetime null comment '消息更新时间',
	constraint kxl_message_id_uindex
		unique (id)
)
;

create index receive_user_index
	on kxl_message_state (receive_user)
;

create table kxl_message_sys
(
	message_id bigint not null comment '系统消息主键'
		primary key,
	message_content varchar(255) not null comment '消息内容',
	message_title varchar(64) null comment '消息标题',
	message_url varchar(128) null comment '消息跳转地址',
	message_extra varchar(128) null comment '消息扩展第字段',
	post_date datetime not null comment '消息生成时间',
	constraint kxl_message_sys_message_id_uindex
		unique (message_id)
)
;

create table kxl_message_template
(
	template_id bigint not null comment '消息模版ID'
		primary key,
	template_code varchar(36) not null comment '模版编码',
	template_name varchar(64) not null comment '模板名称',
	template_context varchar(200) not null comment '模版内容，不能超过200个字符',
	template_url varchar(128) not null comment '模块跳转地址',
	template_app varchar(36) not null comment '模板所属系统',
	template_enable tinyint not null comment '模版是否启用',
	create_time datetime null comment '创建时间',
	update_time datetime null comment '更新时间',
	constraint kxl_message_template_template_id_uindex
		unique (template_id),
	constraint kxl_message_template_template_code_uindex
		unique (template_code)
)
comment '消息系统模版'
;

create table kxl_message_text
(
	message_id bigint not null comment '消息唯一ID'
		primary key,
	message_content varchar(255) not null comment '消息内容，不能为空',
	post_date datetime not null comment '消息发送时间',
	message_title varchar(64) null comment '消息标题',
	creator_id varchar(36) null comment '消息创建人',
	message_type tinyint not null comment '消息类型',
	message_system varchar(36) null comment '消息所属系统',
	message_url varchar(128) null comment '消息跳转的地址',
	message_extra varchar(128) null comment '消息扩展字段',
	constraint kxl_message_text_message_id_uindex
		unique (message_id)
)
;

ALTER TABLE kxl_message_template ADD template_title VARCHAR(36) NULL COMMENT '模版标题';

INSERT INTO `kxl_dev_db`.`kxl_message_template`(`template_id`, `template_code`, `template_name`, `template_context`, `template_url`, `template_app`, `template_enable`, `create_time`, `update_time`, `template_title`) VALUES (462124668539633664, 'cas-conact-email-template', '中科院外网咨询邮件（系统自动发送）', '院所名称：${company} \n您的姓名：${name} \n您的电话：${phone} \n您的邮件：${email}', 'https://www.kingxunlian.com/', 'sco-service', 1, '2018-02-01 15:59:52', NULL, '中科院外网咨询邮件（系统自动发送）');
