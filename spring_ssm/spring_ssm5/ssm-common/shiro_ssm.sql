/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : shiro_ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-08-18 18:20:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pre_Code` varchar(255) NOT NULL,
  `pre_Name` varchar(255) DEFAULT NULL,
  `create_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_User` int(10) DEFAULT NULL,
  `update_User` int(10) DEFAULT NULL,
  `Logic_State` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'P_LIST', '权限列表', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('2', 'P_ADD', '权限添加', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('3', 'P_DELETE', '权限删除', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('4', 'U_LIST', '用户列表', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('5', 'U_ONLINE', '在线用户', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('6', 'U_KILL', '用户Session踢出', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('7', 'U_ACTIVE', '用户激活&禁止', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('8', 'U_DELETE', '用户删除', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('9', 'P_ASSIGNMENT', '权限分配', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('10', 'R_DELETE', '角色删除', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('11', 'R_ADD', '角色添加', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('12', 'R_LIST', '角色列表', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');
INSERT INTO `permission` VALUES ('13', 'R_ASSIGNMENT', '角色分配', '2016-08-18 18:09:10', '2016-08-18 18:09:10', null, null, '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_Code` varchar(255) NOT NULL,
  `role_Name` varchar(255) NOT NULL,
  `create_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_User` int(10) DEFAULT NULL,
  `update_User` int(10) DEFAULT NULL,
  `Logic_State` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROOT', 'root', '2016-08-18 18:17:49', '2016-08-18 18:17:49', null, null, '1');
INSERT INTO `role` VALUES ('2', 'P_MANAGE', '权限管理', '2016-08-18 18:17:49', '2016-08-18 18:17:49', null, null, '1');
INSERT INTO `role` VALUES ('3', 'U_MANAGE', '用户管理', '2016-08-18 18:17:49', '2016-08-18 18:17:49', null, null, '1');

-- ----------------------------
-- Table structure for role_pre
-- ----------------------------
DROP TABLE IF EXISTS `role_pre`;
CREATE TABLE `role_pre` (
  `role_id` int(10) NOT NULL,
  `pre_id` int(10) NOT NULL,
  `create_Date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `create_User` int(10) DEFAULT NULL,
  `update_User` int(10) DEFAULT NULL,
  `Logic_State` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_pre
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `nick_Name` varchar(100) NOT NULL,
  `sex` int(1) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `stauts` int(1) DEFAULT NULL,
  `create_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_User` int(10) DEFAULT NULL,
  `update_User` int(10) DEFAULT NULL,
  `Logic_State` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '1', '29', '12345678901', '', '1', '2016-08-17 15:51:29', '2016-08-17 10:12:04', '1', '1', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `create_Date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_Date` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `create_User` int(10) DEFAULT NULL,
  `update_User` int(10) DEFAULT NULL,
  `Logic_State` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
