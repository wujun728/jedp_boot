/*
Navicat MySQL Data Transfer

Source Server         : 本地机
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : hibernate-plus

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-12-02 11:26:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `demo1` varchar(255) DEFAULT NULL,
  `demo2` varchar(255) DEFAULT NULL,
  `demo3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES ('1', '3', null, null);
INSERT INTO `demo` VALUES ('2', '3', null, null);
INSERT INTO `demo` VALUES ('3', '3', null, null);
