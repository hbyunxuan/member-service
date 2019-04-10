CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员Id',
  `source` varchar(10) NOT NULL COMMENT '来源 alipay,weixin,android,ios，web',
  `deleted` varchar(2) DEFAULT NULL COMMENT '是否删除标记 0 代表未删除，1 代表删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `member_alipay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` bigint(20) NOT NULL COMMENT '会员Id',
  `user_id` varchar(45) NOT NULL COMMENT '平台用户唯一标号',
  `nickname` varchar(45) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `province` varchar(45) DEFAULT NULL COMMENT '省份',
  `city` varchar(45) DEFAULT NULL COMMENT '城市',
  `headimgurl` varchar(200) DEFAULT NULL COMMENT '头像信息',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型',
  `user_real_flag` varchar(4) DEFAULT NULL COMMENT '是否通过实名认证',
  `user_student_flag` varchar(4) DEFAULT NULL COMMENT '是否通过学生认证',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `total_count` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `member_ticket_buyer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` bigint(20) NOT NULL COMMENT '会员Id',
  `idcard` varchar(18) NOT NULL COMMENT '身份证件号',
  `name` varchar(20) DEFAULT NULL COMMENT '购票人姓名',
  `phone` varchar(15) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE `member_weixin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `member_id` bigint(20) NOT NULL COMMENT '会员id',
  `open_id` varchar(45) NOT NULL COMMENT '微信用户唯一标识符',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `avatar_url` varchar(200) DEFAULT NULL COMMENT '用户头像图片的 URL',
  `gender` tinyint(1) DEFAULT NULL COMMENT '用户性别 0未知，1 男性，2女性',
  `country` varchar(20) DEFAULT NULL COMMENT '用户所在国家',
  `city` varchar(40) DEFAULT NULL COMMENT '用户所在城市',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `total_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `session_key` varchar(24) DEFAULT NULL COMMENT '微信会话key 用于加解密',
  `union_id` varchar(28) DEFAULT NULL COMMENT '用户在开发平台唯一id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openId` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COMMENT='微信会员信息表';








