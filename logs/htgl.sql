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
  `mc` varchar(100) DEFAULT NULL COMMENT '',
  `dlh` varchar(50) DEFAULT NULL COMMENT '',
  `mm` varchar(100) DEFAULT NULL COMMENT '',
  `sfzh` varchar(18) DEFAULT NULL COMMENT '',
  `email` varchar(50) DEFAULT NULL COMMENT '',
  `xb` int(2) DEFAULT NULL COMMENT '',
  `csnyr` int(8) DEFAULT NULL COMMENT '',
  `sjh` varchar(11) DEFAULT NULL COMMENT '',
  `txdz` varchar(32) DEFAULT NULL COMMENT '',
  `qx` int(2) DEFAULT NULL COMMENT '',
  `zt` int(2) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

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
  `fjid` varchar(32) DEFAULT NULL COMMENT '',
  `ryid` varchar(32) DEFAULT NULL COMMENT '',
  `zt` int(2) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

-- ----------------------------
-- Records of gg_fjryb
-- ----------------------------


-- ----------------------------
-- Table structure for gg_imgs
-- ----------------------------
DROP TABLE IF EXISTS `gg_imgs`;
CREATE TABLE `gg_imgs` (
  `id` varchar(32) NOT NULL,
  `tpmc` varchar(100) DEFAULT NULL COMMENT '',
  `tpdz` varchar(200) DEFAULT NULL COMMENT '',
  `height` int(6) DEFAULT NULL COMMENT '',
  `width` int(6) DEFAULT NULL COMMENT '',
  `scsj` decimal(14,0) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

-- ----------------------------
-- Records of gg_imgs
-- ----------------------------


-- ----------------------------
-- Table structure for gg_ltfj
-- ----------------------------
DROP TABLE IF EXISTS `gg_ltfj`;
CREATE TABLE `gg_ltfj` (
  `id` varchar(32) NOT NULL,
  `fjh` varchar(16) DEFAULT NULL COMMENT '',
  `fjmc` varchar(50) DEFAULT NULL COMMENT '',
  `fjms` varchar(200) DEFAULT NULL COMMENT '',
  `fjzt` int(2) DEFAULT NULL COMMENT ' ',
  `fjmm` varchar(8) DEFAULT NULL COMMENT '',
  `cjsj` decimal(14,0) DEFAULT NULL COMMENT '',
  `cjr` varchar(32) DEFAULT NULL COMMENT '',
  `cjrmc` varchar(50) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

-- ----------------------------
-- Records of gg_ltfj
-- ----------------------------


-- ----------------------------
-- Table structure for img_log
-- ----------------------------
DROP TABLE IF EXISTS `img_log`;
CREATE TABLE `img_log` (
  `id` varchar(32) NOT NULL,
  `glid` varchar(32) DEFAULT NULL COMMENT '',
  `imgid` varchar(32) DEFAULT NULL COMMENT '',
  `logsj` decimal(14,0) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

-- ----------------------------
-- Records of img_log
-- ----------------------------


-- ----------------------------
-- Table structure for xt_gnb
-- ----------------------------
DROP TABLE IF EXISTS `xt_gnb`;
CREATE TABLE `xt_gnb` (
  `id` varchar(32) NOT NULL,
  `gnmc` varchar(50) DEFAULT NULL COMMENT '',
  `dyqx` int(2) DEFAULT NULL COMMENT '',
  `fid` varchar(32) DEFAULT NULL COMMENT '',
  `ljdz` varchar(100) DEFAULT NULL COMMENT '',
  `gnlb` int(2) DEFAULT NULL COMMENT '',
  `sxh` int(4) DEFAULT NULL COMMENT '',
  `zt` int(2) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

-- ----------------------------
-- Records of xt_gnb
-- ----------------------------
INSERT INTO `xt_gnb` VALUES ('7e372d7e636b417d892f3ed98dc5aa35', '聊天娱乐目录', '2', null, '', '1', '3', '0');
INSERT INTO `xt_gnb` VALUES ('ab62bfb38a4041ab859f6cde2be923a7', '聊天娱乐', '2', '7e372d7e636b417d892f3ed98dc5aa35', 'ltyl/fjgl', '0', '1', '0');
INSERT INTO `xt_gnb` VALUES ('b85a26a3ea0d478784ff359de710bc0b', '好看的功能', '2', null, '', '1', '1', '0');
INSERT INTO `xt_gnb` VALUES ('db6baa61fe8e469db6238c04ccf4537c', '元素周期表', '2', 'b85a26a3ea0d478784ff359de710bc0b', 'view/htgl/yszqb/yszqb.html', '0', '1', '0');

