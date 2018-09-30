-- 根菜单  1个
-- 一级菜单
-- 二级菜单
-- 三级菜单
CREATE TABLE `avenue_menu` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单id',
	`type` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Enum菜单类型：0-根菜单，1一级，2二级，3三级',

	`name` varchar(20) NOT NULL DEFAULT ''  COMMENT '菜单名称',
	`description` varchar(100) NOT NULL DEFAULT ''  COMMENT '菜单介绍',
	`iconfont` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单图标',
	`url` varchar(200) NOT NULL DEFAULT ''  COMMENT '菜单链接',
	`delete_flag` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除',

	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`)
) COMMENT '菜单表';

insert into avenue_menu (`id`,`parent_id`,`type`,`name`,`description`,`iconfont`)values
(1,0,0,"root","根标签","",""),
(2,1,1,"会员管理","desc","&#xe6b8;",""),
(3,1,1,"分类管理","desc","&#xe723;",""),
(4,1,1,"城市联动","desc","&#xe723;",""),
(5,1,1,"图标字体","desc","&#xe6b4;",""),
(6,5,2,"图标对应字体","desc","&#xe6a7;","/common/unicode.html");