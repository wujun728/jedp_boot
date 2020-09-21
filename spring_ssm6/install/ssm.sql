/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-08 17:22:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('yuki', 'oBd1oZisQaYUBflBEPA+Bw==', 'IFq7HRUG+pgf4keuGFkfvQ==', '2016-07-08 14:40:22');

-- ----------------------------
-- Table structure for t_fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_fileinfo`;
CREATE TABLE `t_fileinfo` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  `objectClass` varchar(255) DEFAULT NULL,
  `objectId` bigint(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fileinfo
-- ----------------------------
INSERT INTO `t_fileinfo` VALUES ('1', '20160612105445.png', '324835', 'xxx', '\\068\\173\\fd31863f2867902fc125f21ea2894f23', 'image/png', null, null, '2016-06-12 14:44:34');
INSERT INTO `t_fileinfo` VALUES ('2', '20160612105445.png', '324608', null, '\\068\\173\\fd31863f2867902fc125f21ea2894f23', 'image/png', null, '1', '2016-06-12 15:51:42');
INSERT INTO `t_fileinfo` VALUES ('3', '20160612105445.png', '324816', 'photo', '\\068\\173\\fd31863f2867902fc125f21ea2894f23', 'image/png', 'com.jyl.file.model.FileInfo', '1', '2016-06-12 16:14:12');
INSERT INTO `t_fileinfo` VALUES ('4', '20160612105445.png', '324795', 'photo', '\\068\\173\\fd31863f2867902fc125f21ea2894f23', 'image/png', 'com.jyl.file.model.FileInfo', '1', '2016-06-12 16:54:38');
INSERT INTO `t_fileinfo` VALUES ('5', '20160612105445.png', '326950', 'photo', '\\068\\173\\fd31863f2867902fc125f21ea2894f23', 'image/png', 'com.jyl.file.model.FileInfo', '1', '2016-06-12 16:58:58');
INSERT INTO `t_fileinfo` VALUES ('7', '20160613135817.png', '407801', 'photo', '\\049\\063\\b62f9d8db116eb21780ba0f4cfbd1c4a', 'image/png', 'com.jyl.file.model.FileInfo', '1', '2016-06-13 13:58:37');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '698d51a19d8a121ce581499d7b701668', '24');
INSERT INTO `t_user` VALUES ('2', 'test', '698d51a19d8a121ce581499d7b701668', '16');
INSERT INTO `t_user` VALUES ('3', 'yuki', '698d51a19d8a121ce581499d7b701668', '12');
