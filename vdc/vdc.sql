/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50613
 Source Host           : localhost
 Source Database       : vdc2

 Target Server Version : 50613
 File Encoding         : utf-8

 Date: 08/24/2013 21:18:53 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `customer_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `customer_account_log`;
CREATE TABLE `customer_account_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL,
  `log_by` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `previous_amount` decimal(12,2) DEFAULT NULL,
  `is_recharge` int(11) DEFAULT NULL,
  `change_amount` decimal(12,2) DEFAULT NULL,
  `current_amount` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- ----------------------------
--  Table structure for `customer_info`
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称（个人姓名，店名或者公司名称）',
  `is_company` int(11) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL COMMENT '联系人姓名',
  `telephone` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `account_balance` decimal(12,2) DEFAULT NULL COMMENT '账户余额（用于服务费）',
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `menu_info`
-- ----------------------------
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `parent_menu_id` bigint(20) DEFAULT NULL,
  `menu_description` varchar(255) DEFAULT NULL,
  `menu_uri` varchar(255) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `is_enabled` int(11) DEFAULT NULL,
  `allow_role_id` bigint(20) DEFAULT NULL,
  `limit_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `menu_info`
-- ----------------------------
BEGIN;
INSERT INTO `menu_info` VALUES ('1', '系统管理', null, null, null, '1', '1', '2', null), ('2', '菜单管理', '1', null, '/system/menu/list', '2', '1', null, null), ('3', '角色管理', '1', null, '/system/role/list', '2', '1', null, null), ('4', '用户管理', '1', null, '/system/user/list', '2', '1', null, null), ('5', '客户管理', '1', null, '/system/customer/list', '2', '1', null, null), ('6', '基本设置', null, null, null, '1', '1', '3', null), ('7', '角色管理', '6', null, '/setting/role/list', '2', '1', null, null), ('8', '用户管理', '6', null, '/setting/user/list', '2', '1', null, null), ('9', '店铺设置', '6', null, '/setting/shop/list', '2', '1', null, null), ('10', '仓库设置', '6', null, '/setting/warehouse/list', '2', '1', null, null), ('11', '服务费充值', '6', null, '/setting/recharge', '2', '1', null, null), ('12', '商品管理', null, null, null, '1', '1', null, null), ('13', '商品分类', '12', null, '/goods/category', '2', '1', null, null), ('14', '商品参数', '12', null, '/goods/param', '2', '1', null, null), ('15', '商品列表', '12', null, '/goods/list', '2', '1', null, null), ('16', '订单管理', null, null, null, '1', '1', null, null), ('17', '订单列表', '16', null, '/trade/list', '2', '1', null, null), ('18', '会员管理', null, null, null, '1', '1', null, null), ('19', '会员等级', '18', null, '/member/grade', '2', '1', null, null), ('20', '会员列表', '18', null, '/member/list', '2', '1', null, null), ('21', '统计', null, null, null, '1', '1', null, null), ('22', '销售统计', '21', null, '/report/sales', '2', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `role_info`
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `parent_role_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `is_enabled` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `role_menu_ref`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_ref`;
CREATE TABLE `role_menu_ref` (
  `role_menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `mobilephone` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `is_locked` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
