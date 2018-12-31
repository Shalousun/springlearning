/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50722
Source Host           : 127.0.0.1:3306
Source Database       : project_boot

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-31 23:11:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `article_id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', 'Spring REST Security', 'Spring');
INSERT INTO `t_article` VALUES ('2', 'Java Concurrency', 'Java');
INSERT INTO `t_article` VALUES ('3', 'efz3z4', 'fgtj9z');
INSERT INTO `t_article` VALUES ('4', 'efz3z4', 'fgtj9z');
INSERT INTO `t_article` VALUES ('5', 'redis', 'redis');
INSERT INTO `t_article` VALUES ('6', 'm6ej9s', '7mkg7j');
INSERT INTO `t_article` VALUES ('7', 'w14b6g', 'kv588h');
