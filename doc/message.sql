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



