/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : htgl

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-11-01 17:48:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gg_czry
-- ----------------------------
DROP TABLE IF EXISTS `gg_czry`;
CREATE TABLE `gg_czry` (
  `id` varchar(32) NOT NULL,
  `mc` varchar(100) DEFAULT NULL COMMENT '名称',
  `dlh` varchar(50) DEFAULT NULL COMMENT '登录号',
  `mm` varchar(100) DEFAULT NULL COMMENT '密码',
  `sfzh` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `xb` int(2) DEFAULT NULL COMMENT '性别  0-男  1-女',
  `csnyr` int(8) DEFAULT NULL COMMENT '出生年月日',
  `sjh` varchar(11) DEFAULT NULL COMMENT '手机号',
  `txdz` varchar(32) DEFAULT NULL COMMENT '头像地址id ',
  `qx` int(2) DEFAULT NULL COMMENT '权限 0-超级管理员  1- 管理员  2- 普通人员',
  `zt` int(2) DEFAULT NULL COMMENT '状态 0-正常  1-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作人员表';

-- ----------------------------
-- Records of gg_czry
-- ----------------------------
INSERT INTO `gg_czry` VALUES ('superadmin', '超级管理员', 'superadmin', 'b6589fc6ab0dc82cf12099d1c2d40ab994e8410c', '410369852147896541', 'lyz_0220@163.com', '0', '19950220', '13111111111', '', '0', '0');

-- ----------------------------
-- Table structure for gg_fjryb
-- ----------------------------
DROP TABLE IF EXISTS `gg_fjryb`;
CREATE TABLE `gg_fjryb` (
  `id` varchar(32) NOT NULL,
  `fjid` varchar(32) DEFAULT NULL COMMENT '房间id',
  `ryid` varchar(32) DEFAULT NULL COMMENT '人员id',
  `zt` int(2) DEFAULT NULL COMMENT '房间人员状态  0-正常  1-禁言',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间-人员表';

-- ----------------------------
-- Records of gg_fjryb
-- ----------------------------


-- ----------------------------
-- Table structure for gg_imgs
-- ----------------------------
DROP TABLE IF EXISTS `gg_imgs`;
CREATE TABLE `gg_imgs` (
  `id` varchar(32) NOT NULL,
  `tpmc` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `tpdz` varchar(200) DEFAULT NULL COMMENT '图片地址（绝对路径）',
  `height` int(6) DEFAULT NULL COMMENT '图片高度',
  `width` int(6) DEFAULT NULL COMMENT '图片宽度',
  `scsj` decimal(14,0) DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';

-- ----------------------------
-- Records of gg_imgs
-- ----------------------------


-- ----------------------------
-- Table structure for gg_ltfj
-- ----------------------------
DROP TABLE IF EXISTS `gg_ltfj`;
CREATE TABLE `gg_ltfj` (
  `id` varchar(32) NOT NULL,
  `fjh` varchar(16) DEFAULT NULL COMMENT '房间号',
  `fjmc` varchar(50) DEFAULT NULL COMMENT '房间名称',
  `fjms` varchar(200) DEFAULT NULL COMMENT '房间描述',
  `fjzt` int(2) DEFAULT NULL COMMENT '房间状态   0-正常   1-禁用 ',
  `fjmm` varchar(8) DEFAULT NULL COMMENT '房间密码',
  `cjsj` decimal(14,0) DEFAULT NULL COMMENT '创建时间',
  `cjr` varchar(32) DEFAULT NULL COMMENT '房间创建人，gg_czry表ID',
  `cjrmc` varchar(50) DEFAULT NULL COMMENT '创建人名称，同步gg_czry表名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房间表';

-- ----------------------------
-- Records of gg_ltfj
-- ----------------------------


-- ----------------------------
-- Table structure for img_log
-- ----------------------------
DROP TABLE IF EXISTS `img_log`;
CREATE TABLE `img_log` (
  `id` varchar(32) NOT NULL,
  `glid` varchar(32) DEFAULT NULL COMMENT '关联的id（例如人员表的id，或者其他用到图片的表的id）',
  `imgid` varchar(32) DEFAULT NULL COMMENT '图片id',
  `logsj` decimal(14,0) DEFAULT NULL COMMENT '日志创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片日志表';

-- ----------------------------
-- Records of img_log
-- ----------------------------


-- ----------------------------
-- Table structure for xt_gnb
-- ----------------------------
DROP TABLE IF EXISTS `xt_gnb`;
CREATE TABLE `xt_gnb` (
  `id` varchar(32) NOT NULL,
  `gnmc` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `dyqx` int(2) DEFAULT NULL COMMENT '对应权限',
  `fid` varchar(32) DEFAULT NULL COMMENT '所属功能，是那个功能的子功能，为空是顶级功能',
  `ljdz` varchar(100) DEFAULT NULL COMMENT '功能的跳转地址',
  `gnlb` int(2) DEFAULT NULL COMMENT '功能类别：0-节点 1-目录  目录没有跳转地址',
  `sxh` int(4) DEFAULT NULL COMMENT '顺序号',
  `zt` int(2) DEFAULT NULL COMMENT '使用状态 0-正常 1-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统功能表';

-- ----------------------------
-- Records of xt_gnb
-- ----------------------------
INSERT INTO `xt_gnb` VALUES ('7e372d7e636b417d892f3ed98dc5aa35', '聊天娱乐目录', '2', null, '', '1', '3', '0');
INSERT INTO `xt_gnb` VALUES ('ab62bfb38a4041ab859f6cde2be923a7', '聊天娱乐', '2', '7e372d7e636b417d892f3ed98dc5aa35', 'ltyl/fjgl', '0', '1', '0');
INSERT INTO `xt_gnb` VALUES ('b85a26a3ea0d478784ff359de710bc0b', '好看的功能', '2', null, '', '1', '1', '0');
INSERT INTO `xt_gnb` VALUES ('db6baa61fe8e469db6238c04ccf4537c', '元素周期表', '2', 'b85a26a3ea0d478784ff359de710bc0b', 'view/htgl/yszqb/yszqb.html', '0', '1', '0');

