-- ----------------------------
-- Table structure for broker_message_log
-- ----------------------------
    DROP TABLE IF EXISTS `broker_message_log`;
    CREATE TABLE `broker_message_log` (
      `message_id` varchar(255) NOT NULL COMMENT '消息唯一ID',
      `message` varchar(4000) NOT NULL COMMENT '消息内容',
      `try_count` int(4) DEFAULT '0' COMMENT '重试次数',
      `status` varchar(10) DEFAULT '' COMMENT '消息投递状态 0投递中，1投递成功，2投递失败',
      `next_retry` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '下一次重试时间',
      `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
      `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
      PRIMARY KEY (`message_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
    DROP TABLE IF EXISTS `t_order`;
    CREATE TABLE `t_order` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(255) DEFAULT NULL,
      `message_id` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2018091102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for error_ack_message
-- ----------------------------    
    
    DROP TABLE IF EXISTS `error_ack_message`;
    CREATE TABLE `error_ack_message` (
     `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
      `error_method` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '错误方法',
      `error_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '错误信息',
      `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
      `message` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息实体',
      `create_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
      `modify_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
      `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
      PRIMARY KEY (`id`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


DROP TABLE IF EXISTS `t_send_message`;
CREATE TABLE `t_send_message` (
  `id` bigint NOT NULL AUTO_INCREMENT comment '消息主键',
  `message_id` varchar(38) NOT NULL  COMMENT '消息唯一id',
  `sender` varchar(255) DEFAULT NULL comment '消息发送者',
  `sender_id` varchar(38) DEFAULT NULL COMMENT '消息发送者id',
  `receive_id` varchar(38) DEFAULT NULL COMMENT '接受者ID',
  `message` text DEFAULT NULL COMMENT '消息体',
  `message_type` char(2) NOT NULL COMMENT '消息类型',
  `annex_id` varchar(38) DEFAULT NULL COMMENT '附件id',
  `status` char(1) DEFAULT '1' COMMENT '消息状态,1 有效，0 无效',
  `create_time` TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP COMMENT '修改时间',
  `remarks` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2020101022 DEFAULT CHARSET=utf8 comment '发送消息';