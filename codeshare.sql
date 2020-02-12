/*
Navicat MySQL Data Transfer

Source Server         : connect
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : codeshare

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-12-30 21:33:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `intro` text,
  `field` varchar(20) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `downTimes` int(11) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('3', '2', 'douban_log.ldf', 'dzy', 'intro', '文本摘要', 'D:/codeRepo/douban_log.ldf', '1', '2019-12-18 15:32:36');
INSERT INTO `code` VALUES ('5', '2', 'odm_ref.txt', '', '', '情感分析', 'D:/codeRepo/odm_ref.txt', '0', '2019-12-18 15:37:51');
INSERT INTO `code` VALUES ('6', '2', 'auto_fit.cpp', 'dzy', '', '知识图谱', 'D:/codeRepo/auto_fit.cpp', '1', '2019-12-18 17:06:38');
INSERT INTO `code` VALUES ('8', '2', '例7-1.cpp', 'user', '7-1.cpp的代码简介', '推荐系统', 'D:/codeRepo/例7-1.cpp', '0', '2019-12-21 11:53:44');
INSERT INTO `code` VALUES ('9', '2', '例7-5.cpp', '佚名', 'qwer', '文本摘要', 'D:/codeRepo/例7-5.cpp', '0', '2019-12-21 16:17:44');
INSERT INTO `code` VALUES ('10', '2', 'detect_part2.cpp', '董泽宇', '用于检测XXX的第二部分代码', '知识图谱', 'D:/codeRepo/detect_part2.cpp', '0', '2019-12-30 21:25:46');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `clazz` tinyint(4) DEFAULT NULL,
  `otherid` int(11) DEFAULT NULL,
  `comment` text,
  `com_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '3', '1', '1', '怎么是准考证？', '2019-12-20 15:19:01');
INSERT INTO `comment` VALUES ('2', '2', '1', '1', '发表评论测试', '2019-12-20 16:03:36');
INSERT INTO `comment` VALUES ('3', '2', '1', '1', '三楼', '2019-12-20 16:19:19');
INSERT INTO `comment` VALUES ('4', '2', '1', '1', '四楼', '2019-12-20 16:19:35');
INSERT INTO `comment` VALUES ('5', '2', '1', '1', '五楼', '2019-12-20 16:29:04');
INSERT INTO `comment` VALUES ('6', '2', '1', '2', '沙发', '2019-12-20 16:42:05');
INSERT INTO `comment` VALUES ('7', '2', '2', '6', '第一条评论', '2019-12-21 10:21:59');
INSERT INTO `comment` VALUES ('8', '2', '2', '3', '这是数据库课设的数据库文件', '2019-12-21 10:22:33');
INSERT INTO `comment` VALUES ('9', '2', '3', '8', '对数据集评论测试', '2019-12-21 11:52:46');
INSERT INTO `comment` VALUES ('10', '13', '3', '8', '其他用户评论', '2019-12-21 12:04:47');
INSERT INTO `comment` VALUES ('11', '2', '2', '5', '测试评论1', '2019-12-21 16:17:13');

-- ----------------------------
-- Table structure for `dataset`
-- ----------------------------
DROP TABLE IF EXISTS `dataset`;
CREATE TABLE `dataset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `intro` text,
  `field` varchar(20) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `downTimes` int(11) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dataset
-- ----------------------------
INSERT INTO `dataset` VALUES ('4', '2', 'opals使用.txt', 'dzy', 'pfj', '对话系统', 'D:/dataRepo/opals使用.txt', '0', '2019-12-18 17:05:23');
INSERT INTO `dataset` VALUES ('6', '2', 'freeglut_staticd.lib', 'dzy', '这是一个数据集简介', '推荐系统', 'D:/dataRepo/freeglut_staticd.lib', '0', '2019-12-19 16:43:40');
INSERT INTO `dataset` VALUES ('8', '2', 'freeglut-3.2.1.tar.gz', 'dzy', '数据集简介简介简介简介简介简介简介简介简介简介简介简介', '情感分析', 'D:/dataRepo/freeglut-3.2.1.tar.gz', '1', '2019-12-19 16:49:11');
INSERT INTO `dataset` VALUES ('10', '2', 'freeglutd.dll', 'freeglut', 'OpenGL中的freeglut', '推荐系统', 'D:/dataRepo/freeglutd.dll', '0', '2019-12-30 21:27:01');

-- ----------------------------
-- Table structure for `field`
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `fieldCN` varchar(20) NOT NULL DEFAULT '',
  `fieldEN` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`fieldCN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES ('信息检索', 'Information Retrieval');
INSERT INTO `field` VALUES ('对话系统', 'Conversational Systems');
INSERT INTO `field` VALUES ('情感分析', 'Sentiment analysis');
INSERT INTO `field` VALUES ('推荐系统', 'Recommendation System');
INSERT INTO `field` VALUES ('文本摘要', 'Text Summaization');
INSERT INTO `field` VALUES ('知识图谱', 'Knowledge Graph');
INSERT INTO `field` VALUES ('社交分析', 'Social Analysis');
INSERT INTO `field` VALUES ('问答系统', 'Question Answering');

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `author` varchar(256) DEFAULT NULL,
  `abstarct` text,
  `journal` varchar(100) DEFAULT NULL,
  `field` varchar(20) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `downTimes` int(11) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES ('1', '2', '2019年下半年英语六级笔试准考证(董泽宇).pdf', 'dzy', '这是一段摘要，测试摘要显示', '', '信息检索', 'D:/myupload/2019年下半年英语六级笔试准考证(董泽宇).pdf', '4', '2019-12-18 09:50:15');
INSERT INTO `paper` VALUES ('2', '2', '四六级核心语法速成.pdf', 'dzy', '这是一段摘要，测试摘要显示', '', '信息检索', 'D:/myupload/四六级核心语法速成.pdf', '1', '2019-12-18 09:55:04');
INSERT INTO `paper` VALUES ('3', '2', 'loginpic.jpg', 'dzy', '这是一段摘要，测试摘要显示', '', '信息检索', 'D:/myupload/loginpic.jpg', '0', '2019-12-18 10:13:48');
INSERT INTO `paper` VALUES ('9', '2', '2019-0310_flow_toAuthor_Content_2_95696.docx', 'dzy', '这是一段摘要，测试摘要显示', '', '信息检索', 'D:/myupload/2019-0310_flow_toAuthor_Content_2_95696.docx', '0', '2019-12-18 11:41:48');
INSERT INTO `paper` VALUES ('10', '2', '发票.pdf', 'dzy', '这是一段摘要，测试摘要显示', '', '信息检索', 'D:/myupload/发票.pdf', '0', '2019-12-18 11:44:11');
INSERT INTO `paper` VALUES ('11', '2', '附件2：结课作业模板.docx', 'dzy', null, 'XX作业报', '推荐系统', 'D:/myupload/附件2：结课作业模板.docx', '0', '2019-12-30 21:29:54');

-- ----------------------------
-- Table structure for `papercode`
-- ----------------------------
DROP TABLE IF EXISTS `papercode`;
CREATE TABLE `papercode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paperid` int(11) DEFAULT NULL,
  `codeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of papercode
-- ----------------------------
INSERT INTO `papercode` VALUES ('1', '2', '3');
INSERT INTO `papercode` VALUES ('3', '1', '6');
INSERT INTO `papercode` VALUES ('5', '2', '8');
INSERT INTO `papercode` VALUES ('7', '1', '10');

-- ----------------------------
-- Table structure for `paperdata`
-- ----------------------------
DROP TABLE IF EXISTS `paperdata`;
CREATE TABLE `paperdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paperid` int(11) DEFAULT NULL,
  `dataid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paperdata
-- ----------------------------
INSERT INTO `paperdata` VALUES ('1', '3', '4');
INSERT INTO `paperdata` VALUES ('2', '1', '6');
INSERT INTO `paperdata` VALUES ('3', '1', '8');
INSERT INTO `paperdata` VALUES ('5', '2', '10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `clazz` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '0');
INSERT INTO `user` VALUES ('2', 'dzy', '123', '1');
INSERT INTO `user` VALUES ('3', 'user1', 'user1', '1');
INSERT INTO `user` VALUES ('4', 'user2', 'user2', '1');
INSERT INTO `user` VALUES ('12', 'user', 'passwd', '1');
INSERT INTO `user` VALUES ('13', 'auser', 'auser', '1');
INSERT INTO `user` VALUES ('14', 'banned', '123', '2');
