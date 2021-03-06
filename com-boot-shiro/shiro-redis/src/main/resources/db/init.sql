-- ----------------------------
-- Table structure for T_PERMISSION
-- ----------------------------
CREATE TABLE `SCOTT`.`T_PERMISSION` (
    ID BIGINT NOT NULL primary key  COMMENT '主键',
    URL VARCHAR(256) NULL COMMENT 'url地址',
    NAME VARCHAR(66) NULL COMMENT '地址描述'
)
-- ----------------------------
-- Records of T_PERMISSION
-- ----------------------------
INSERT INTO SCOTT.T_PERMISSION VALUES (1, '/user', 'user:user');
INSERT INTO SCOTT.T_PERMISSION VALUES (2, '/user/add', 'user:add');
INSERT INTO SCOTT.T_PERMISSION VALUES (3, '/user/delete', 'user:delete');

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
CREATE TABLE `SCOTT`.`T_ROLE`(
    ID BIGINT NOT NULL primary key  COMMENT '主键',
    NAME VARCHAR(32) NULL COMMENT '角色名称',
    MEMO VARCHAR(32) NULL COMMENT '角色描述'
)
-- ----------------------------
-- Records of T_ROLE
-- ----------------------------
INSERT INTO SCOTT.T_ROLE VALUES (1, 'admin', '超级管理员');
INSERT INTO SCOTT.T_ROLE VALUES (2, 'test', '测试账户');

-- ----------------------------
-- Table structure for T_ROLE_PERMISSION
-- ----------------------------
CREATE TABLE SCOTT.T_ROLE_PERMISSION (
    RID BIGINT NULL COMMENT '角色id',
    PID BIGINT NULL COMMENT '权限id'
)

-- ----------------------------
-- Records of T_ROLE_PERMISSION
-- ----------------------------
INSERT INTO SCOTT.T_ROLE_PERMISSION VALUES (1, 2);
INSERT INTO SCOTT.T_ROLE_PERMISSION VALUES (1, 3);
INSERT INTO SCOTT.T_ROLE_PERMISSION VALUES (2, 1);
INSERT INTO SCOTT.T_ROLE_PERMISSION VALUES (1, 1);

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE `SCOTT`.`T_USER` (
   ID BIGINT NOT NULL primary key COMMENT '主键',
   USERNAME VARCHAR(20) NOT NULL COMMENT '用户名',
   PASSWD VARCHAR(128) NOT NULL COMMENT '密码',
   CREATE_TIME DATE NULL COMMENT '创建时间',
   STATUS CHAR NOT NULL COMMENT '是否有效 1：有效  0：锁定'
);

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO SCOTT.T_USER VALUES (2, 'test', '7a38c13ec5e9310aed731de58bbc4214', DATE('2017-11-19 17:20:21'), '0');
INSERT INTO SCOTT.T_USER VALUES (1, 'zbcn', '42ee25d1e43e9f57119a00d0a39e5250', DATE('2017-11-19 10:52:48'), '1');

-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
CREATE TABLE `SCOTT`.`T_USER_ROLE`(
    USER_ID BIGINT NULL COMMENT '用户id',
    ROLE_ID BIGINT NULL COMMENT '角色id'
);
-- ----------------------------
-- Records of T_USER_ROLE
-- ----------------------------
INSERT INTO SCOTT.T_USER_ROLE VALUES (1, 1);
INSERT INTO SCOTT.T_USER_ROLE VALUES (2, 2);