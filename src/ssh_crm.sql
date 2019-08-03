/*
Navicat MySQL Data Transfer

Source Server         : LearnMySQL
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ssh_crm

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-08-03 14:43:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `custName` varchar(255) DEFAULT NULL,
  `custSource` varchar(255) DEFAULT NULL,
  `custPhone` varchar(255) DEFAULT NULL,
  `custMobile` varchar(255) DEFAULT NULL,
  `custLevel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `FKo6oqtbbjmu6890to85xbpymcd` (`custLevel`),
  CONSTRAINT `FKo6oqtbbjmu6890to85xbpymcd` FOREIGN KEY (`custLevel`) REFERENCES `t_dict` (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '杨志昊', '微信介绍', '123', '333', '2');
INSERT INTO `t_customer` VALUES ('2', '杜汉涛', '小广告', '212', '131', '2');
INSERT INTO `t_customer` VALUES ('3', '胡荣耀', '小广告', '343', '666', '1');
INSERT INTO `t_customer` VALUES ('4', '尹俊锋', '微信介绍', '10086', '1737535', '3');
INSERT INTO `t_customer` VALUES ('5', '刘迎刚', '91秦先生', '22', '33', '1');
INSERT INTO `t_customer` VALUES ('6', '小徐', '微信', '333', '2222', '3');

-- ----------------------------
-- Table structure for `t_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `did` varchar(255) NOT NULL,
  `dname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('1', '普通客户');
INSERT INTO `t_dict` VALUES ('2', 'VIP客户');
INSERT INTO `t_dict` VALUES ('3', '超级VIP客户');

-- ----------------------------
-- Table structure for `t_linkman`
-- ----------------------------
DROP TABLE IF EXISTS `t_linkman`;
CREATE TABLE `t_linkman` (
  `linkid` int(11) NOT NULL AUTO_INCREMENT,
  `lkmName` varchar(255) DEFAULT NULL,
  `lkmGender` varchar(255) DEFAULT NULL,
  `lkmPhone` varchar(255) DEFAULT NULL,
  `lkmMobile` varchar(255) DEFAULT NULL,
  `clid` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkid`),
  KEY `FKinxafh64kvgx8vfajibp3fvgy` (`clid`),
  CONSTRAINT `FKinxafh64kvgx8vfajibp3fvgy` FOREIGN KEY (`clid`) REFERENCES `t_customer` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_linkman
-- ----------------------------
INSERT INTO `t_linkman` VALUES ('1', '蔡蔡', '男', '250', '500', '4');
INSERT INTO `t_linkman` VALUES ('2', '老哥', '男', '123', '123', '1');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'lucy', '250', '安徽');
INSERT INTO `t_user` VALUES ('2', '小王', '333', '南京');
INSERT INTO `t_user` VALUES ('3', '小李', '555', '合肥');
INSERT INTO `t_user` VALUES ('4', '小尹', '222', '六安');

-- ----------------------------
-- Table structure for `t_visit`
-- ----------------------------
DROP TABLE IF EXISTS `t_visit`;
CREATE TABLE `t_visit` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `vaddress` varchar(255) DEFAULT NULL,
  `vcontent` varchar(255) DEFAULT NULL,
  `uvid` int(11) DEFAULT NULL,
  `cvid` int(11) DEFAULT NULL,
  PRIMARY KEY (`vid`),
  KEY `FKnbsiiqbdcpvtugygwe4vj42s0` (`uvid`),
  KEY `FKfq1vs5t876wufr6l5tctnopps` (`cvid`),
  CONSTRAINT `FKfq1vs5t876wufr6l5tctnopps` FOREIGN KEY (`cvid`) REFERENCES `t_customer` (`cid`),
  CONSTRAINT `FKnbsiiqbdcpvtugygwe4vj42s0` FOREIGN KEY (`uvid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_visit
-- ----------------------------
INSERT INTO `t_visit` VALUES ('1', '滁州学院', '培训', '4', '4');
