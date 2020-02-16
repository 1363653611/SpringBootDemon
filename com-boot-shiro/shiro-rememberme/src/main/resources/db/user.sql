-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE `SCOTT`.`T_USER` (
   ID BIGINT NOT NULL primary key COMMENT '用户名',
   USERNAME VARCHAR(20) NOT NULL COMMENT '密码',
   PASSWD VARCHAR(128) NOT NULL COMMENT '密码',
   CREATE_TIME DATE NULL COMMENT '创建时间',
   STATUS CHAR NOT NULL COMMENT '是否有效 1：有效  0：锁定'
);

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO SCOTT.T_USER VALUES (2, 'test', '7a38c13ec5e9310aed731de58bbc4214', DATE('2017-11-19 17:20:21'), '0');
INSERT INTO SCOTT.T_USER VALUES (1, 'zbcn', '42ee25d1e43e9f57119a00d0a39e5250', DATE('2017-11-19 10:52:48'), '1');