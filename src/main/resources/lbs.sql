#CREATE DATABASE `lbs`;
#
USE `lbs`;
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`(
	`user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`account` VARCHAR(64) NOT NULL COMMENT '账号',
	`user_name` VARCHAR(64) NOT NULL COMMENT '用户昵称',
	`password` VARCHAR(64) NOT NULL COMMENT '密码',
	`sex` CHAR(4) NOT NULL COMMENT '性别',
	`head_image` VARCHAR(128) COMMENT '头像',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`user_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`(
	`role_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
	`role_name` VARCHAR(64) NOT NULL COMMENT '角色名称',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`role_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`(
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '关联表id',
	`user_id` INT(11) NOT NULL COMMENT '用户id',
	`role_id` INT(11) NOT NULL COMMENT '角色id',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户-角色关联表';

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`(
	`permission_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
	`permission_name` VARCHAR(64) NOT NULL COMMENT '权限名称',
	`description` VARCHAR(64) NOT NULL COMMENT '权限描述',
	`url` VARCHAR(128) NOT NULL COMMENT '路径',
	`order_index` INT(4) NOT NULL COMMENT '排序id',
	`parent_id` INT(11) NOT NULL COMMENT '父节点',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`permission_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role`(
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '关联表id',
	`role_id` INT(11) NOT NULL COMMENT '角色id',
	`permission_id` INT(11) NOT NULL COMMENT '权限id',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色-权限关联表';

DROP TABLE IF EXISTS `map_mark_message`;
CREATE TABLE `map_mark_message`(
	`mark_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '地图标注id',
	`user_id` INT(11) NOT NULL COMMENT '用户id',
	`lat` DOUBLE(10,6) NOT NULL COMMENT '纬度',
	`lng` DOUBLE(10,6) NOT NULL COMMENT '经度',
	`title` VARCHAR(64) NOT NULL COMMENT '标题',
	`content` TEXT NOT NULL COMMENT '内容',
	`status` TINYINT(4) NOT NULL COMMENT '发布推广状态 0未申请推广 1审核通过 2未通过 3待审核',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`mark_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='地图标注信息表';

DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention`(
	`attention_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '关注表id',
	`user_id` INT(11) NOT NULL COMMENT '用户id',
	`user_attention_id` INT(11) NOT NULL COMMENT '用户关注对象的id',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`attention_id`),
	KEY `idex_user_id` (`user_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='关注表';

DROP TABLE IF EXISTS `experience`;
CREATE TABLE `experience`(
	`experience_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '心得id',
	`user_id` INT(11) NOT NULL COMMENT '用户id',
	`title` VARCHAR(64) NOT NULL COMMENT '心得标题',
	`content` TEXT NOT NULL COMMENT '心得内容',
	`like_num` INT(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
	`unlike_num` INT(11) NOT NULL DEFAULT '0' COMMENT '不赞成数',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`experience_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='心得表';

DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`(
	`reply_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '回复表id',
	`user_id` INT(11) NOT NULL COMMENT '用户id',
	`experience_id` INT(11) NOT NULL COMMENT '心得id',
	`content` TEXT NOT NULL COMMENT '内容',
	`like_num` INT(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
	`unlike_num` INT(11) NOT NULL DEFAULT '0' COMMENT '不赞成数',
	`main_reply_id` INT(11) NOT NULL COMMENT '主回复id',
	`parent_user_id` INT(11) NOT NULL COMMENT '上级用户id',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(`reply_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='回复表';





