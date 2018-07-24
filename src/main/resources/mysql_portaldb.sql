/*
 Navicat Premium Data Transfer

 Source Server         : Common_Dev_MariaDB
 Source Server Type    : MySQL
 Source Server Version : 100122
 Source Host           : 115.68.46.219:3306
 Source Schema         : portaldb

 Target Server Type    : MySQL
 Target Server Version : 100122
 File Encoding         : 65001

 Date: 11/07/2018 14:59:48
*/
CREATE DATABASE /*!32312 IF NOT EXISTS*/`portaldb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `portaldb`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` text,
  `file_size` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `question_no` int(11) NOT NULL,
  `answerer` varchar(128) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for auto_scaling_config
-- ----------------------------
DROP TABLE IF EXISTS `auto_scaling_config`;
CREATE TABLE `auto_scaling_config` (
  `no` int(11) NOT NULL,
  `guid` varchar(255) NOT NULL,
  `org` varchar(255) DEFAULT NULL,
  `space` varchar(255) DEFAULT NULL,
  `app` varchar(255) DEFAULT NULL,
  `instance_min_cnt` int(11) NOT NULL,
  `instance_max_cnt` int(11) NOT NULL,
  `cpu_threshold_min_per` decimal(2,1) NOT NULL,
  `cpu_threshold_max_per` decimal(2,1) NOT NULL,
  `memory_min_size` int(11) DEFAULT NULL,
  `memory_max_size` int(11) DEFAULT NULL,
  `check_time_sec` int(11) NOT NULL,
  `auto_increase_yn` varchar(1) DEFAULT NULL,
  `auto_decrease_yn` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `content` longtext,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` text,
  `file_size` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `parent_no` int(11) NOT NULL,
  `group_no` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for board_comment
-- ----------------------------
DROP TABLE IF EXISTS `board_comment`;
CREATE TABLE `board_comment` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) NOT NULL,
  `content` longtext NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `board_no` int(11) NOT NULL,
  `parent_no` int(11) NOT NULL,
  `group_no` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for buildpack_category
-- ----------------------------
DROP TABLE IF EXISTS `buildpack_category`;
CREATE TABLE `buildpack_category` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `description` text,
  `buildpack_name` varchar(255) NOT NULL,
  `thumb_img_name` varchar(255) DEFAULT NULL,
  `thumb_img_path` text,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `app_sample_file_name` varchar(255) DEFAULT NULL,
  `app_sample_file_path` text,
  `app_sample_file_size` bigint(20) DEFAULT NULL,
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `doc_file_url` text,
  `tags_param` text,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=19165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buildpack_category
-- ----------------------------
BEGIN;
INSERT INTO `buildpack_category` VALUES (3158, 'GO 앱 개발환경', 'buildpack_system', 'GO 오프라인 앱 개발환경', 'Go 웹 앱을 쉽게 개발, 배치, 스케일링합니다.', 'go_buildpack', 'Go.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/9614a17898ca41cd9904b61add6f5385-1527590255327-R28uanBn', 'Y', 'go-15-sample.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/3a9d4d0dac1641aaaf52ab60e1f761e2-1527590256340-Z28tMTUtc2FtcGxlLnppcA%3D%3D', 1421, 'admin', '2016-07-26 07:41:23', '2018-05-29 19:37:37', 'https://golang.org/', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (3155, '전자정부 프레임워크 앱 개발환경', 'buildpack_system', '전자정부 프레임워크 오프라인 앱 개발환경', '전자정부 프레임워크 오프라인 앱 개발환경으로 Tomcat 및  Jboss 애플리케이션 서버를 선택하여 구성할수 있다.', 'egov_buildpack', '????????????.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/2b0239c48593464092e27514d2168e8f-1527590579185-Pz8_Pz8_Pz8_Pz8_LnBuZw%3D%3D', 'Y', 'spring-music.war', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a5b1f59d68e74f25938f38572246bb36-1527767558364-c3ByaW5nLW11c2ljLndhcg%3D%3D', 25679220, 'admin', '2016-07-26 07:25:25', '2018-05-31 20:54:34', NULL, '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `buildpack_category` VALUES (3161, 'Java Pinpoint 오프라인 앱 개발환경', 'buildpack_custom', 'Java Pinpoint 오프라인 APM 앱 개발환경', 'Java 어플이케이션 모니터링을 위한 Pinpoint 오프라인 개발환경입니다.\nNaver 에서 만든 분산환경 애플리케이션 모니터링입니다.\n', 'pinpoint_buildpack', 'getImage.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/ad904d7493aa4177ab88346ba6d84c4a-1527589599144-Z2V0SW1hZ2UucG5n', 'Y', 'spring-music.war', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/68780461bd5e48aba3a8f23af9128a63-1527589597821-c3ByaW5nLW11c2ljLndhcg%3D%3D', 25679220, 'admin', '2016-07-26 07:47:17', '2018-05-29 19:27:58', 'https://naver.github.io/pinpoint/', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (772, 'Ruby 앱 개발환경', 'buildpack_system', 'Ruby 오프라인 앱 개발환경', 'Ruby on Rails 웹 앱을 쉽게 개발, 배치, 스케일링합니다.', 'ruby_buildpack', 'ruby.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/08556b0b42bb44a5a2fcbfed1697d062-1527590687285-cnVieS5qcGc%3D', 'Y', 'ruby-sample.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/553bf55fc08a428597c9d887645ed131-1527590687559-cnVieS1zYW1wbGUuemlw', 56955, 'admin', '2016-07-21 01:32:10', '2018-05-29 19:44:47', 'https://www.ruby-lang.org/ko/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `buildpack_category` VALUES (3156, 'Java8 온라인 앱 개발환경', 'buildpack_system', 'Java8 온라인 앱 개발환경', 'Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.', 'java_buildpack', 'java.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D', 'Y', 'sample-spring.war', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D', 9478983, 'admin', '2016-07-26 07:27:43', '2018-05-29 19:43:55', 'https://www.java.com/ko/download/faq/java8.xml', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (20, 'Java8 오프라인 앱 개발환경', 'buildpack_system', 'Java8 오프라인 앱 개발환경', 'Java8 오프라인 환경에서 Tomcat 애플리케이션 서버로 자바 웹 어플리케이션 설행 환경을 구성해준다.', 'java_buildpack_offline', 'java.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/03adfd8c2b7b42e5ad5a61b0e96aa417-1527590940156-amF2YS5qcGc%3D', 'Y', 'sample-spring.war', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/d6b266dd233243d8953f8375ebf4a91c-1525840906106-c2FtcGxlLXNwcmluZy53YXI%3D', 9478983, 'admin', '2016-07-19 09:08:43', '2018-06-01 05:54:08', 'https://www.java.com/ko/download/faq/java8.xml', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `buildpack_category` VALUES (665, 'mysql test', 'buildpack_system', 'mysql test 112', 'mysql test 212', 'mysql', 'img6186877999400462681.png', 'http://52.201.48.51:8080/v1/KEY_508666cb499b4970a5030fb067cb3557/portal-container/eff84057b38e47f7b4a4c0ded9a2878a.png', 'N', 'hello-spring-mysql.war', 'http://52.201.48.51:8080/v1/KEY_84586dfdc15e4f8b9c2a8e8090ed9810/portal-container/7aad407fee54477ba01a40346be3d84d.war', 8700607, 'admin', '2016-09-05 17:55:39', '2017-11-27 00:29:34', 'https://www.mysql.com/', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (3159, 'Python 앱 개발환경', 'buildpack_system', 'Python 오프라인 앱 개발환경', 'Python 웹 앱을 쉽게 개발, 배치, 스케일링합니다.', 'python_buildpack', 'python.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/d0b0f782a92742e78f17727533c5ce76-1527590295260-cHl0aG9uLnBuZw%3D%3D', 'Y', 'python-sample.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a9537bd6310048b0bdef8461ba555a76-1527590296367-cHl0aG9uLXNhbXBsZS56aXA%3D', 3195, 'admin', '2016-07-26 07:44:40', '2018-05-29 19:38:17', 'https://www.python.org/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `buildpack_category` VALUES (3160, 'PHP 앱 개발환경', 'buildpack_system', 'PHP 오프라인 앱 개발환경', 'PHP 웹 앱을 쉽게 개발, 배치, 스케일링합니다.', 'php_buildpack', 'PHP.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/f27ee323f082430894c5a42365e7a682-1527590360448-UEhQLmpwZw%3D%3D', 'Y', 'php-sample.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0be7ae9b04714b67b97ad26c6286156e-1527590361399-cGhwLXNhbXBsZS56aXA%3D', 84439, 'admin', '2016-07-26 07:45:11', '2018-05-29 19:39:22', 'http://php.net/', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (1150, 'ruby test', 'buildpack_system', 'ruby test 11', 'ruby test 21', 'ruby_buildpack', 'java.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D', 'N', 'sample-spring.war', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/fa2e7c036cb34404846933da0f971f2d-1527590622396-c2FtcGxlLXNwcmluZy53YXI%3D', 9478983, 'admin', '2016-09-06 15:39:08', '2018-05-29 19:44:12', 'https://www.ruby-lang.org/ko/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `buildpack_category` VALUES (3157, 'Nodejs 앱 개발환경', 'buildpack_system', 'Nodejs 오프라인 앱 개발환경', 'Node.js 웹 앱을 쉽게 개발, 배치, 스케일링합니다.', 'nodejs_buildpack', 'nodejs.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/53b96dbb59ac4d82977b586b89fa82f2-1527590468020-bm9kZWpzLnBuZw%3D%3D', 'Y', 'nodejs-sample.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0fe491de1bca4de09eb3303dd0cac7d7-1527590469011-bm9kZWpzLXNhbXBsZS56aXA%3D', 1291728, 'admin', '2016-07-26 07:40:26', '2018-05-29 19:41:09', 'https://nodejs.org/ko/', '{\"community\":\"colors7\",\"free\":\"colors1\"}');
INSERT INTO `buildpack_category` VALUES (19142, 'nginx', 'buildpack_system', 'nginx summary', 'nginx desc', 'staticfile_buildpack', 'nginx.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/22f6cced33bd46288a8652d324c3b0a4-1527235956505-bmdpbnguanBn', 'Y', 'sampleApp-static.zip', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/54ac1159640d4de2b56fe25f317f2ea5-1527235957525-c2FtcGxlQXBwLXN0YXRpYy56aXA%3D', 1300846, 'admin', '2018-05-25 17:12:37', '2018-05-25 17:12:37', NULL, '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for catalog_history
-- ----------------------------
DROP TABLE IF EXISTS `catalog_history`;
CREATE TABLE `catalog_history` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_no` int(11) NOT NULL,
  `catalog_type` varchar(255) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=2550 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for code_detail
-- ----------------------------
DROP TABLE IF EXISTS `code_detail`;
CREATE TABLE `code_detail` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(36) NOT NULL,
  `value` text NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `group_id` varchar(36) NOT NULL,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `order` int(11) NOT NULL DEFAULT '1',
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_detail
-- ----------------------------
BEGIN;
INSERT INTO `code_detail` VALUES (10, 'documents_starter', '앱 템플릿', NULL, 'DOCUMENTS_CLASSIFICATION', 'Y', 1, 'admin', '2016-09-01 13:45:32', '2016-09-01 13:45:32');
INSERT INTO `code_detail` VALUES (11, 'documents_build_pack', '앱 개발환경', NULL, 'DOCUMENTS_CLASSIFICATION', 'Y', 2, 'admin', '2016-09-01 13:45:32', '2016-09-01 13:45:32');
INSERT INTO `code_detail` VALUES (12, 'documents_service_pack', '서비스', NULL, 'DOCUMENTS_CLASSIFICATION', 'Y', 3, 'admin', '2016-09-01 13:45:32', '2016-09-01 13:45:32');
INSERT INTO `code_detail` VALUES (13, 'documents_etc', '기타', NULL, 'DOCUMENTS_CLASSIFICATION', 'Y', 4, 'admin', '2016-09-01 13:45:32', '2016-09-01 13:45:32');
INSERT INTO `code_detail` VALUES (14, '2', '거부', '이메일승인거부', 'USER_STATUS', 'Y', 3, 'admin', '2016-08-02 04:56:27', '2016-08-02 04:56:27');
INSERT INTO `code_detail` VALUES (15, '3', '삭제', '삭제회원', 'USER_STATUS', 'Y', 4, 'admin', '2016-08-02 04:56:27', '2016-08-02 04:56:27');
INSERT INTO `code_detail` VALUES (16, 'minCpu', '20', 'cpu 최소', 'USER_AUTOSCAILE', 'Y', 4, 'admin', '2016-07-26 01:53:42', '2016-07-26 08:02:02');
INSERT INTO `code_detail` VALUES (17, 'maxCpu', '80', 'cpu 최대', 'USER_AUTOSCAILE', 'Y', 3, 'admin', '2016-07-26 01:53:42', '2016-07-26 08:02:02');
INSERT INTO `code_detail` VALUES (18, '1', '승인', '이메일승인완료', 'USER_STATUS', 'Y', 2, 'admin', '2016-08-02 04:56:27', '2016-08-02 04:56:27');
INSERT INTO `code_detail` VALUES (19, 'ORG MANAGER', '조직 관리자', 'Can invite users and manage user roles in the org ', 'ORG_ROLES', 'Y', 1, 'admin', '2016-09-02 01:17:19', '2016-09-02 01:22:38');
INSERT INTO `code_detail` VALUES (20, '0', '생성', '이메일승인대기', 'USER_STATUS', 'Y', 1, 'admin', '2016-08-02 04:56:27', '2016-08-02 04:57:19');
INSERT INTO `code_detail` VALUES (21, 'minInstance', '2', '인스턴스 최소', 'USER_AUTOSCAILE', 'Y', 1, 'admin', '2016-07-26 01:44:17', '2016-07-26 08:33:01');
INSERT INTO `code_detail` VALUES (22, 'maxInstance', '20', '인스턴스 최대', 'USER_AUTOSCAILE', 'Y', 2, 'admin', '2016-07-26 01:44:44', '2016-07-26 08:33:29');
INSERT INTO `code_detail` VALUES (23, 'service_storage', '데이터 저장소', ' 데이터 저장소', 'SERVICE_PACK_CATALOG', 'Y', 0, 'admin', '2016-07-25 17:06:24', '2018-04-10 13:18:30');
INSERT INTO `code_detail` VALUES (24, 'service_message', '메시징', ' ', 'SERVICE_PACK_CATALOG', 'Y', 2, 'admin', '2016-07-25 17:06:24', '2016-08-25 00:11:49');
INSERT INTO `code_detail` VALUES (25, 'service_nosql', 'NoSQL 데이터베이스', ' ', 'SERVICE_PACK_CATALOG', 'Y', 3, 'admin', '2016-07-25 17:06:24', '2016-08-25 00:11:56');
INSERT INTO `code_detail` VALUES (26, 'service_rdbms', '관계형 데이터베이스', ' ', 'SERVICE_PACK_CATALOG', 'Y', 4, 'admin', '2016-07-25 17:06:24', '2016-08-25 00:12:04');
INSERT INTO `code_detail` VALUES (67, 'starter_main', '기본유형', 'start_main', 'STARTER_CATALOG', 'Y', 0, 'admin', '2018-04-06 13:58:06', '2018-04-10 11:13:57');
INSERT INTO `code_detail` VALUES (68, '1111', '2222', 'ssdfsdf', 'ssdfsdfsd', 'Y', 1, 'admin', '2018-04-06 05:02:36', '2018-04-06 05:02:36');
INSERT INTO `code_detail` VALUES (28, 'service_monitoring', '어플리케이션 모니터링', ' ', 'SERVICE_PACK_CATALOG', 'Y', 5, 'admin', '2016-07-26 05:49:10', '2016-08-25 00:12:12');
INSERT INTO `code_detail` VALUES (29, 'buildpack_custom', '사용자 앱 개발환경', '사용자가 스스로 구성한 앱 개발 환경으로 지금 앱을 시작합니다.', 'BUILD_PACK_CATALOG', 'Y', 2, 'admin', '2016-07-25 17:05:32', '2016-08-25 00:12:52');
INSERT INTO `code_detail` VALUES (30, 'buildpack_system', '시스템 앱 개발환경', '파스-타에서 기본적으로 제공하는 앱 개발 환경으로 지금 앱을 시작합니다.', 'BUILD_PACK_CATALOG', 'Y', 1, 'admin', '2016-07-25 17:05:32', '2016-08-25 11:05:38');
INSERT INTO `code_detail` VALUES (31, 'notice_event', '이벤트', '이벤트 공지', 'SUPPORT_NOTICE', 'Y', 2, 'admin', '2016-08-23 17:55:10', '2016-08-24 10:14:05');
INSERT INTO `code_detail` VALUES (32, 'notice_notice', '공지', '공지사항', 'SUPPORT_NOTICE', 'Y', 1, 'admin', '2016-08-23 17:54:47', '2016-08-24 10:14:05');
INSERT INTO `code_detail` VALUES (33, 'notice_problem', '장애', '장애 공지', 'SUPPORT_NOTICE', 'Y', 3, 'admin', '2016-08-23 17:55:59', '2016-08-24 10:14:05');
INSERT INTO `code_detail` VALUES (34, 'answered', '답변완료', '답변완료 상태', 'QUESTION_STATUS', 'Y', 2, 'admin', '2016-08-24 13:13:50', '2016-08-24 13:14:39');
INSERT INTO `code_detail` VALUES (35, 'question_etc', '기타', NULL, 'QUESTION_CLASSIFICATION', 'Y', 4, 'admin', '2016-08-24 09:52:08', '2016-08-24 09:56:39');
INSERT INTO `code_detail` VALUES (36, 'question_service_pack', '서비스', NULL, 'QUESTION_CLASSIFICATION', 'Y', 3, 'admin', '2016-08-24 09:52:01', '2016-08-24 09:56:51');
INSERT INTO `code_detail` VALUES (37, 'question_build_pack', '앱 개발환경', NULL, 'QUESTION_CLASSIFICATION', 'Y', 2, 'admin', '2016-08-24 09:51:18', '2016-08-24 09:56:59');
INSERT INTO `code_detail` VALUES (38, 'question_starter', '앱 템플릿', NULL, 'QUESTION_CLASSIFICATION', 'N', 1, 'admin', '2016-08-24 09:50:57', '2016-08-24 09:57:08');
INSERT INTO `code_detail` VALUES (39, 'waiting', '미답변', '미답변 상태', 'QUESTION_STATUS', 'Y', 1, 'admin', '2016-08-24 13:11:39', '2016-08-24 13:23:05');
INSERT INTO `code_detail` VALUES (40, 'dev_ops', '개발 지원 도구', ' ', 'SERVICE_PACK_CATALOG', 'Y', 6, 'admin', '2017-12-21 04:29:23', NULL);
COMMIT;

-- ----------------------------
-- Table structure for code_group
-- ----------------------------
DROP TABLE IF EXISTS `code_group`;
CREATE TABLE `code_group` (
  `id` varchar(35) NOT NULL,
  `name` text NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `user_id` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_group
-- ----------------------------
BEGIN;
INSERT INTO `code_group` VALUES ('USER_AUTOSCAILE', '사용자포탈 자동스케일 ', '2016-07-26 01:43:43', '2018-04-05 11:00:03', 'admin');
INSERT INTO `code_group` VALUES ('USER_STATUS', '사용자상태코드', '2016-08-02 04:50:15', '2016-08-02 04:50:15', 'admin');
INSERT INTO `code_group` VALUES ('SERVICE_PACK_CATALOG', '서비스 카탈로그', '2016-07-25 17:03:16', '2018-04-05 21:32:14', 'admin');
INSERT INTO `code_group` VALUES ('BUILD_PACK_CATALOG', '앱 개발환경 카탈로그', '2016-07-25 17:03:16', '2016-08-23 03:41:16', 'admin');
INSERT INTO `code_group` VALUES ('QUESTION_STATUS', '답변 상태 코드', '2016-08-24 13:10:39', '2016-08-24 13:10:39', 'admin');
INSERT INTO `code_group` VALUES ('DOCUMENTS_CLASSIFICATION', '문서 분류코드', '2016-09-01 13:43:04', '2018-04-05 11:00:00', 'admin');
INSERT INTO `code_group` VALUES ('ORG_ROLES', '조직 역할', '2016-09-02 01:16:18', '2016-09-02 01:16:18', 'admin');
INSERT INTO `code_group` VALUES ('QUESTION_CLASSIFICATION', '문의분류코드', '2016-08-24 09:38:21', '2016-08-24 09:38:21', 'admin');
INSERT INTO `code_group` VALUES ('SUPPORT_NOTICE', '공지사항 분류코드', '2016-08-24 09:42:12', '2016-08-24 10:14:05', 'admin');
INSERT INTO `code_group` VALUES ('APP_MEMORY_SIZE', '앱 메모리 사이즈', '2016-12-05 15:20:48', '2018-04-05 18:00:52', 'admin');
INSERT INTO `code_group` VALUES ('STARTER_CATALOG', '스타터 개발환경 카탈로그', '2018-04-06 13:56:09', '2018-04-06 13:56:12', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `name` varchar(128) NOT NULL,
  `value` varchar(128) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES ('email_auth_yn', 'test', NULL, '2018-02-23 14:16:35');
INSERT INTO `config_info` VALUES ('smtp_url', 'mail.google.com', NULL, '2018-06-01 02:07:10');
INSERT INTO `config_info` VALUES ('web_ide_url', 'http://115.68.46.183:8080,http://115.68.46.184:8080', NULL, '2018-06-01 02:07:13');
INSERT INTO `config_info` VALUES ('api_url', 'https://login.115.68.46.30.xip.io', NULL, '2018-06-01 02:19:01');
INSERT INTO `config_info` VALUES ('uaa_url', 'https://login.115.68.46.30.xip.io/', NULL, '2018-06-01 02:19:05');
INSERT INTO `config_info` VALUES ('user_portal_url', 'http://portal-web-admin.115.68.46.30.xip.io/', NULL, '2018-06-01 02:07:07');
COMMIT;

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `content` longtext,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` text,
  `file_size` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for history_store
-- ----------------------------
DROP TABLE IF EXISTS `history_store`;
CREATE TABLE `history_store` (
  `timemark` datetime NOT NULL,
  `table_name` varchar(50) NOT NULL,
  `pk_date_src` text NOT NULL,
  `pk_date_dest` text NOT NULL,
  `record_state` smallint(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history_store
-- ----------------------------
BEGIN;
INSERT INTO `history_store` VALUES ('2018-02-22 19:00:31', 'user_detail', '<user_id>test1234</user_id>', '<user_id>test1234</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:01:56', 'buildpack_category', '<no>3160</no>', '<no>3160</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:02:44', 'buildpack_category', '<no>3159</no>', '<no>3159</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:03:21', 'buildpack_category', '<no>3158</no>', '<no>3158</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:04:18', 'buildpack_category', '<no>3156</no>', '<no>3156</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:04:24', 'catalog_history', '<no>2456</no>', '<no>2456</no>', 1);
INSERT INTO `history_store` VALUES ('2018-02-22 19:01:49', 'user_detail', '<user_id>edu04</user_id>', '<user_id>edu04</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:05:22', 'buildpack_category', '<no>3157</no>', '<no>3157</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:06:21', 'buildpack_category', '<no>3155</no>', '<no>3155</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:07:51', 'buildpack_category', '<no>1150</no>', '<no>1150</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:08:53', 'buildpack_category', '<no>20</no>', '<no>20</no>', 2);
INSERT INTO `history_store` VALUES ('2018-02-19 18:11:18', 'catalog_history', '<no>2457</no>', '<no>2457</no>', 1);
INSERT INTO `history_store` VALUES ('2018-02-20 10:28:11', 'user_detail', '<user_id>d3g3nyo@gmail.com</user_id>', '<user_id>d3g3nyo@gmail.com</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-20 19:42:46', 'auto_scaling_config', '<no>1</no>', '<no>1</no>', 1);
INSERT INTO `history_store` VALUES ('2018-02-20 22:45:53', 'auto_scaling_config', '<no>2</no>', '<no>2</no>', 1);
INSERT INTO `history_store` VALUES ('2018-02-22 19:03:53', 'user_detail', '<user_id>edu05</user_id>', '<user_id>edu05</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 19:06:35', 'user_detail', '<user_id>hyunlee@bludeigm.com</user_id>', '<user_id>hyunlee@bludeigm.com</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 19:22:35', 'user_detail', '<user_id>leeinjeong</user_id>', '<user_id>leeinjeong</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 20:05:53', 'user_detail', '<user_id>edu</user_id>', '<user_id>edu</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 20:13:38', 'user_detail', '<user_id>di01</user_id>', '<user_id>di01</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 20:18:57', 'user_detail', '<user_id>di02</user_id>', '<user_id>di02</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 22:21:41', 'user_detail', '<user_id>swmoon</user_id>', '<user_id>swmoon</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 23:26:59', 'user_detail', '<user_id>hrjin1</user_id>', '<user_id>hrjin1</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 00:11:07', 'user_detail', '<user_id>edu03</user_id>', '<user_id>edu03</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 00:33:01', 'user_detail', '<user_id>edu99</user_id>', '<user_id>edu99</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 00:33:50', 'user_detail', '<user_id>rex-admin</user_id>', '<user_id>rex-admin</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 15:56:06', 'web_ide_user', '<org_name>파스타</org_name>', '<org_name>파스타</org_name>', 1);
INSERT INTO `history_store` VALUES ('2018-02-23 02:49:15', 'user_detail', '<user_id>rex-user</user_id>', '<user_id>rex-user</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 09:48:07', 'catalog_history', '<no>2458</no>', '<no>2458</no>', 1);
INSERT INTO `history_store` VALUES ('2018-02-23 18:55:44', 'user_detail', '<user_id>leeinjeong-member</user_id>', '<user_id>leeinjeong-member</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 18:55:55', 'user_detail', '<user_id>eduxx</user_id>', '<user_id>eduxx</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 18:56:40', 'user_detail', '<user_id>demo</user_id>', '<user_id>demo</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 18:57:03', 'user_detail', '<user_id>hrjin</user_id>', '<user_id>hrjin</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 18:57:08', 'user_detail', '<user_id>gnc</user_id>', '<user_id>gnc</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 19:29:43', 'user_detail', '<user_id>lena</user_id>', '<user_id>lena</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 22:00:06', 'user_detail', '<user_id>admin</user_id>', '<user_id>admin</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-23 22:02:31', 'user_detail', '<user_id>sjchoi</user_id>', '<user_id>sjchoi</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 03:41:31', 'user_detail', '<user_id>yschoi</user_id>', '<user_id>yschoi</user_id>', 2);
INSERT INTO `history_store` VALUES ('2018-02-22 19:00:25', 'user_detail', '<user_id>pch1234</user_id>', '<user_id>pch1234</user_id>', 2);
COMMIT;

-- ----------------------------
-- Table structure for invite_org_space
-- ----------------------------
DROP TABLE IF EXISTS `invite_org_space`;
CREATE TABLE `invite_org_space` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id of invite org-space user table (auto increment)',
  `token` varchar(128) NOT NULL COMMENT 'inviting org id ',
  `gubun` varchar(128) NOT NULL,
  `invite_id` bigint(20) NOT NULL COMMENT 'inviting user id',
  `role_name` varchar(128) NOT NULL COMMENT 'org role permisson(OrgManager(4) / BillingManager(2) / OrgAuditor(1))',
  `invite_user_id` varchar(256) NOT NULL COMMENT 'space role permisson(JSON type, {"spaceName": (number : SpaceManager(4) / SpaceDeveloper(2) / SpaceAuditor(1)) })',
  `user_id` varchar(128) NOT NULL COMMENT 'recommended user id recently',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated at (update time)',
  `access_cnt` bigint(20) NOT NULL DEFAULT '0' COMMENT 'inviting count',
  `invite_name` varchar(128) NOT NULL,
  `setyn` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'answer check for inviting user',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for invite_user
-- ----------------------------
DROP TABLE IF EXISTS `invite_user`;
CREATE TABLE `invite_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id of invite org-space user table (auto increment)',
  `user_id` varchar(128) NOT NULL,
  `gubun` varchar(8) NOT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `token` varchar(128) NOT NULL,
  `role` text NOT NULL,
  `org_guid` varchar(100) NOT NULL,
  `invite_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `parent_no` int(11) NOT NULL,
  `sort_no` int(11) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `menu_path` text NOT NULL,
  `image_path` text,
  `open_window_yn` varchar(1) NOT NULL DEFAULT 'N',
  `login_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `description` longtext,
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=1937 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, 0, 1, '대시보드', '/org/orgMain', NULL, 'N', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (1802, 1, 1, 'MENU', '#', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-11-18 11:20:50', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (110, 2, 3, 'menu 1', '#', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-10-06 15:29:55', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (111, 2, 2, 'test 2-2', '#', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-10-06 15:34:18', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (8, 2, 4, 'menu 2', '#', NULL, 'Y', 'N', 'N', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (7, 2, 1, 'test 2-1', '#', NULL, 'Y', 'N', 'N', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:45');
INSERT INTO `menu` VALUES (3, 0, 3, '도움말', 'https://github.com/PaaS-TA/Guide-2.0-Linguine-', NULL, 'Y', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (2, 0, 2, '카탈로그', '/catalog/catalogMain', NULL, 'N', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (9, 5, 1, 'test 5-1', '#', NULL, 'Y', 'N', 'N', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (5, 0, 4, '문서', '/documents/documentsMain', NULL, 'N', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (704, 6, 1, 'MENU', '#', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-10-18 05:25:37', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (6, 0, 5, '공지', '/notice/noticeMain', NULL, 'N', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (4, 0, 6, '커뮤니티', '/board/boardMain', NULL, 'N', 'Y', 'Y', NULL, 'admin', '2016-09-29 02:30:28', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (559, 0, 7, 'NEW_MAIN', '/usage/testMain', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-10-14 05:22:30', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (1702, 0, 8, 'NEW_CATALOG', '/catalog/catalogTestMain', NULL, 'N', 'N', 'N', NULL, 'admin', '2016-11-17 15:08:13', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (1936, 0, 10, 'MENU22', '#', NULL, 'N', 'Y', 'N', NULL, 'admin', '2016-12-05 14:42:38', '2018-01-31 05:30:46');
INSERT INTO `menu` VALUES (1935, 0, 9, 'MENU', '#', NULL, 'N', 'Y', 'N', NULL, 'admin', '2016-12-05 14:21:33', '2018-01-31 05:30:46');
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `important` varchar(5) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `content` longtext,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` text,
  `file_size` bigint(20) DEFAULT NULL,
  `start_date` varchar(20) NOT NULL,
  `end_date` varchar(20) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES (128, 'PaaS-TA 오픈 안내', 'true', 'notice_notice', 'Y', 'PaaS-TA 오픈 안내', 'PaaSTA 포털 코딩룰.docx', 'http://52.201.48.51:8080/v1/KEY_84586dfdc15e4f8b9c2a8e8090ed9810/portal-container/deff0f4dccd141368235b7e16fafa59d.docx', 83869, '2016/09/02', '2016/10/31', '2016-09-02 13:29:19', '2016-09-02 14:39:23');
COMMIT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `content` longtext NOT NULL,
  `cell_phone` varchar(11) DEFAULT NULL,
  `status` varchar(8) NOT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` text,
  `file_size` bigint(20) DEFAULT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for servicepack_category
-- ----------------------------
DROP TABLE IF EXISTS `servicepack_category`;
CREATE TABLE `servicepack_category` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `description` text,
  `service_name` varchar(255) NOT NULL,
  `thumb_img_name` varchar(255) DEFAULT NULL,
  `thumb_img_path` text,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `parameter` text,
  `app_bind_parameter` text,
  `dashboard_use_yn` varchar(10) NOT NULL DEFAULT 'N',
  `app_bind_yn` varchar(10) NOT NULL DEFAULT 'N',
  `doc_file_url` text,
  `tags_param` text,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=37903 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servicepack_category
-- ----------------------------
BEGIN;
INSERT INTO `servicepack_category` VALUES (1756, 'Redis 서비스', 'service_nosql', 'Redis NoSQL 및 In memory 서비스', '<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n', 'redis', 'redis???.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn', 'Y', 'admin', '2016-07-22 14:45:36', '2018-06-01 06:10:17', '{\"test\":\"text\"}', '{\"sdfsdfsdfsdf\":\"text\"}', 'N', 'Y', 'https://redis.io/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (37873, '오라클 서비스', 'service_rdbms', '오라클 서비스 브로커', '오라클 서비스 브로커 테스트용..', 'oracle', 'oracle-icon.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/787f6c0166b449ac87c5fdb02e1eb03d-1527673829475-b3JhY2xlLWljb24uanBn', 'Y', 'admin', '2018-04-19 06:32:18', '2018-05-30 18:50:29', '{}', '{\"패스워드\":\"password\",\"admin\":\"text\"}', 'N', 'Y', 'https://www.oracle.com/kr/index.html', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (8, 'Mongo-DB DBMS 서비스', 'service_nosql', 'Mongo-DB 도큐먼트 지향 NoSQL DBMS', 'MongoDB는 크로스 플랫폼 도큐먼트 지향 데이터베이스 시스템이며 NoSQL 데이터베이스로 분류되는 MongoDB는 JSON과 같은 동적 스키마형 문서들을 선호함에 따라 전통적인 테이블 기반 관계형 데이터베이스 구조의 사용을 삼간다. ', 'Mongo-DB', 'mongodb.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/cd7d6968a6a145d5bd872cf5afe64ea6-1527674037922-bW9uZ29kYi5wbmc%3D', 'N', 'admin', '2016-07-19 11:12:45', '2018-05-30 18:53:58', '{}', '{}', 'N', 'Y', 'https://www.mongodb.com/cloud/atlas/lp/general?jmp=search&utm_source=google&utm_campaign=APAC-South-Korea-MongoDB-to-Atlas-Brand-Alpha&utm_keyword=mongodb&utm_device=c&utm_network=g&utm_medium=cpc&utm_creative=257451941910&utm_matchtype=e&_bt=257451941910&_bk=mongodb&_bm=e&_bn=g&gclid=EAIaIQobChMI_OzJztmv2wIVkR0rCh3AFQKqEAAYASAAEgJWGvD_BwE', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (1130, 'RabbitMQ 메시지 큐 서비스', 'service_message', 'RabbitMQ는 AMQP를 구현한 비동기 메시지 큐 ', 'AMQP(Advanced Message Queuing Protocol)을 사용한 오픈소스 비동기방식 메시지 지향 미들웨어이다.', 'p-rabbitmq', 'rabbitmq.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/8e34b6c5cbb4422d96e67d965a730496-1527674004459-cmFiYml0bXEuanBn', 'Y', 'admin', '2016-07-22 10:50:06', '2018-05-30 18:53:24', '{}', '{}', 'N', 'Y', 'https://www.rabbitmq.com/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (3126, 'Java 애플리케이션 APM 서비스', 'service_monitoring', 'Java 애플리케이션 대규모 분산 시스템 성능을 분석', 'Pinpoint는 자바 애플리케이션 대규모 분산 시스템의 성능을 분석하고 문제를 진단, 처리하는 플랫폼입니다. ', 'delivery-pipeline-v2', 'java.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/8a8845b768674a4b9ac2b569916b448b-1527673944279-amF2YS5qcGc%3D', 'Y', 'admin', '2016-07-26 05:50:50', '2018-05-30 18:52:24', '{\"owner\":\"default\"}', '{\"bind_param_test\":\"text\"}', 'N', 'N', 'https://www.oracle.com/kr/java/index.html', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (36498, '테스트-servicepack-pinpoint', 'service_monitoring', '테스트-servicepack-pinpoint s', '테스트-servicepack-pinpoint d', 'Pinpoint', 'img6186877999400462681.png', 'http://52.201.48.51:8080/v1/KEY_15e60e0f97194ca7bc7b574de7dddcbb/portal-container/0271db406fdf48e4bc93129fcecaea5e.png', 'N', 'admin', '2016-12-05 10:51:47', '2017-11-27 00:30:25', '{\"application_name\":\"text\"}', '', 'N', 'Y', 'https://naver.github.io/pinpoint/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (37869, '파이프라인-dev', 'dev_ops', '개발용으로 만들어진 파이프라인', '<p>개발용으로 만들어진 파이프라인</p>\n', 'delivery-pipeline-v2', 'pipeline.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/c7c9825efc3f4a90939b9a50f44bdaa7-1527673858875-cGlwZWxpbmUucG5n', 'Y', 'admin', '2018-01-04 05:10:24', '2018-06-22 06:22:34', '{\"owner\":\"default\"}', '{}', 'N', 'N', 'https://github.com/PaaS-TA/DELIVERY-PIPELINE-UI', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (3124, '큐브리드 DBMS 서비스', 'service_rdbms', '큐브리드 관계형 DBMS 서비스', '<p>큐브리드는 관계형 DBMS로서 엔터프라이즈 시장에서 요구하는 대용량 데이터 처리 능력 및 성능, 안정성, 가용성, 관리 편의성을 제공하고 있다.</p>\n', 'CubridDB', '????.jpg', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/870c23172fbb4857b5a5a37c0fd03fdb-1527673954333-Pz8_Py5qcGc%3D', 'Y', 'admin', '2016-07-26 05:44:57', '2018-06-04 13:20:53', '{}', '{}', 'N', 'Y', 'https://www.cubrid.org/manual/en/10.1/api/python.html', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (37866, '형상관리', 'dev_ops', '형상관리', '형상관리 서비스로써 GIT 과 SVN 레파지토리를 제공합니다. ', 'p-paasta-sourcecontrol', 'scm.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/1924391bae694431af5c40033f9154f7-1527673873770-c2NtLnBuZw%3D%3D', 'Y', 'admin', '2017-11-24 16:27:33', '2018-05-30 18:51:14', '{\"owner\":\"default\",\"org_name\":\"default\"}', '{\"test\":\"text\"}', 'N', 'N', 'https://github.com/PaaS-TA/Guide-3.0-Penne-/blob/master/Service-Guide/Tools/PaaS-TA%20%ED%98%95%EC%83%81%EA%B4%80%EB%A6%AC%20%EC%84%9C%EB%B9%84%EC%8A%A4%ED%8C%A9%20%EC%84%A4%EC%B9%98%20%EA%B0%80%EC%9D%B4%EB%93%9C_v1.0.md', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
INSERT INTO `servicepack_category` VALUES (3123, 'Mqsql DBMS 서비스', 'service_rdbms', 'Mysql 관계형 DBMS 서비스', 'MySQL은 가장 많이 쓰이는 오픈 소스의 관계형 데이터베이스 관리 시스템(RDBMS)이고 다중 스레드, 다중 사용자 형식의 구조질의어 형식의 데이터베이스 관리 시스템이다.', 'Mysql-DB', 'mysqldb.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/6f1d8a1ee2a140fe990c782751ead855-1527673967193-bXlzcWxkYi5wbmc%3D', 'Y', 'admin', '2016-07-26 04:32:32', '2018-05-30 18:52:47', '{\"test\":\"text\"}', '{}', 'N', 'Y', 'https://www.mysql.com/', '{\"paasta\":\"colors6\",\"pay\":\"colors2\"}');
COMMIT;

-- ----------------------------
-- Table structure for starter_buildpack_relation
-- ----------------------------
DROP TABLE IF EXISTS `starter_buildpack_relation`;
CREATE TABLE `starter_buildpack_relation` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `starter_category_no` int(11) NOT NULL,
  `buildpack_category_no` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=35767 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of starter_buildpack_relation
-- ----------------------------
BEGIN;
INSERT INTO `starter_buildpack_relation` VALUES (35598, 35644, 19073);
INSERT INTO `starter_buildpack_relation` VALUES (31385, 31431, 20);
INSERT INTO `starter_buildpack_relation` VALUES (35641, 16893, 665);
INSERT INTO `starter_buildpack_relation` VALUES (82, 128, 3157);
INSERT INTO `starter_buildpack_relation` VALUES (9563, 9609, 3155);
INSERT INTO `starter_buildpack_relation` VALUES (35766, 127, 20);
INSERT INTO `starter_buildpack_relation` VALUES (31891, 31937, 20);
INSERT INTO `starter_buildpack_relation` VALUES (35599, 31937, 20);
INSERT INTO `starter_buildpack_relation` VALUES (35632, 0, 3161);
INSERT INTO `starter_buildpack_relation` VALUES (35631, 0, 19108);
INSERT INTO `starter_buildpack_relation` VALUES (35623, 0, 19109);
INSERT INTO `starter_buildpack_relation` VALUES (35621, 0, 19109);
INSERT INTO `starter_buildpack_relation` VALUES (35620, 0, 19109);
INSERT INTO `starter_buildpack_relation` VALUES (35749, 35659, 3160);
INSERT INTO `starter_buildpack_relation` VALUES (35727, 35727, 3160);
INSERT INTO `starter_buildpack_relation` VALUES (35765, 35730, 19161);
INSERT INTO `starter_buildpack_relation` VALUES (35751, 35726, 0);
INSERT INTO `starter_buildpack_relation` VALUES (35753, 35729, 19161);
COMMIT;

-- ----------------------------
-- Table structure for starter_category
-- ----------------------------
DROP TABLE IF EXISTS `starter_category`;
CREATE TABLE `starter_category` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `classification` varchar(36) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `description` longtext,
  `thumb_img_name` varchar(255) DEFAULT NULL,
  `thumb_img_path` text,
  `use_yn` varchar(1) NOT NULL DEFAULT 'Y',
  `user_id` varchar(128) NOT NULL,
  `created` datetime NOT NULL,
  `lastmodified` datetime DEFAULT NULL,
  `tags_param` text,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=35731 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of starter_category
-- ----------------------------
BEGIN;
INSERT INTO `starter_category` VALUES (35644, '테스트-starter-pinpoint', 'starter_main', '테스트-starter-pinpoint s', '테스트-starter-pinpoint d', 'img7406960927791723656.png', 'http://52.201.48.51:8080/v1/KEY_15e60e0f97194ca7bc7b574de7dddcbb/portal-container/2e564d41fedc4a04845cdf993a1ac6e9.png', 'N', 'admin', '2016-12-05 10:53:50', '2017-11-27 00:25:00', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (31431, '템플릿 테스트', 'starter_main', '템플릿 테스트', '', '캡처.PNG', 'http://52.201.48.51:8080/v1/KEY_508666cb499b4970a5030fb067cb3557/portal-container/5f20e16e96a741e8a10ffa7381effc3c.PNG', 'N', 'admin', '2016-11-03 15:44:21', '2017-11-27 00:25:04', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (16893, 'starter mysql test', 'starter_main', 'mysql test 12', 'mysql test 23', '', 'http://52.201.48.51:8080/v1/KEY_508666cb499b4970a5030fb067cb3557/portal-container/a4beb57d63484bea93c94df8760c54db.png', 'N', 'admin', '2018-04-06 01:30:15', '2018-04-06 01:30:15', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (128, 'NodeJS + Mongo-DB 앱 템플릿', 'starter_main', 'NodeJS + Mongo-DB 앱 템플릿', 'NodeJS 앱 개발 환경과 No-SQL Mongo DB 서비스를 사용하여 애플리케이션을 개발합니다.', 'Node+mongodb.png', 'http://10.30.131.12:10008/v1/KEY_65a45a92376949e2a756e511e0d92d2b/portal-container/0d169298733b4cd0b73b270940b6d31c.png', 'N', 'admin', '2016-07-20 14:55:42', '2017-11-27 00:26:21', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (9609, '전자정부 프레임워크 웹 애플리케이션 템플릿', 'starter_main', '전자정부 프레임워크 웹 애플리케이션 앱 템플릿', '전자정부 프레임워크 앱 개발 환경과 함께 Mysql DB 서비스를 사용하고 Redis 및 Object 저장소를 사용합니다.', '전자정부프레임워크빌드팩.png', 'http://10.30.131.12:10008/v1/KEY_65a45a92376949e2a756e511e0d92d2b/portal-container/7272c77bc5234267b2b7e22bbbb0525a.png', 'N', 'admin', '2016-08-22 07:18:29', '2017-11-27 06:23:56', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (127, 'Java + Mysql', 'starter_main', 'Java Tomcat 환경의 MysqlDB  앱 템플릿', '자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.', 'getImage.png', 'http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n', 'Y', 'admin', '2016-07-20 14:54:38', '2018-06-25 01:28:55', '{\"paasta\":\"colors6\"}');
INSERT INTO `starter_category` VALUES (31937, '자바 기본유형 앱 템플릿', 'starter_main', 'Java와 MySQL 기반 웹 애플리케이션 실행환경 템플릿', 'Java와 MySQL 기반 웹 애플리케이션 실행환경입니다. \n', '앱스타터이미지.png', 'http://10.30.131.12:10008/v1/KEY_65a45a92376949e2a756e511e0d92d2b/portal-container/526e4f620d6745ada797ee5b3e859777.png', 'N', 'admin', '2016-11-09 01:06:29', '2017-11-27 06:25:32', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
INSERT INTO `starter_category` VALUES (35659, '자바 기본유형 앱 템플릿', 'starter_main', 'Java와 MySQL 기반 웹 애플리케이션 실행환경 템플릿1', 'Java와 MySQL 기반 웹 애플리케이션 실행환경입니다. 111\n1111111111111', '', 'http://10.30.131.12:10008/v1/KEY_65a45a92376949e2a756e511e0d92d2b/portal-container/526e4f620d6745ada797ee5b3e859777.png', 'N', 'admin', '2018-04-10 17:48:32', '2018-06-04 14:14:40', '{\"paasta\":\"colors6\",\"free\":\"colors1\"}');
COMMIT;

-- ----------------------------
-- Table structure for starter_servicepack_relation
-- ----------------------------
DROP TABLE IF EXISTS `starter_servicepack_relation`;
CREATE TABLE `starter_servicepack_relation` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `starter_category_no` int(11) NOT NULL,
  `servicepack_category_no` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=794464 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of starter_servicepack_relation
-- ----------------------------
BEGIN;
INSERT INTO `starter_servicepack_relation` VALUES (794218, 35644, 36498);
INSERT INTO `starter_servicepack_relation` VALUES (794219, 31431, 8);
INSERT INTO `starter_servicepack_relation` VALUES (794220, 31431, 3124);
INSERT INTO `starter_servicepack_relation` VALUES (794221, 31431, 3125);
INSERT INTO `starter_servicepack_relation` VALUES (794445, 35659, 3126);
INSERT INTO `starter_servicepack_relation` VALUES (794226, 128, 8);
INSERT INTO `starter_servicepack_relation` VALUES (794229, 9609, 1756);
INSERT INTO `starter_servicepack_relation` VALUES (794230, 9609, 3125);
INSERT INTO `starter_servicepack_relation` VALUES (794231, 9609, 3123);
INSERT INTO `starter_servicepack_relation` VALUES (794463, 127, 3123);
INSERT INTO `starter_servicepack_relation` VALUES (794279, 0, 3124);
INSERT INTO `starter_servicepack_relation` VALUES (794278, 0, 37877);
INSERT INTO `starter_servicepack_relation` VALUES (794277, 0, 3126);
INSERT INTO `starter_servicepack_relation` VALUES (794276, 0, 3124);
INSERT INTO `starter_servicepack_relation` VALUES (794450, 35729, 37900);
INSERT INTO `starter_servicepack_relation` VALUES (794281, 0, 37877);
INSERT INTO `starter_servicepack_relation` VALUES (794280, 0, 3126);
INSERT INTO `starter_servicepack_relation` VALUES (35727, 35727, 3126);
INSERT INTO `starter_servicepack_relation` VALUES (794299, 16893, 3123);
INSERT INTO `starter_servicepack_relation` VALUES (794462, 35730, 37900);
INSERT INTO `starter_servicepack_relation` VALUES (794449, 35727, 3123);
INSERT INTO `starter_servicepack_relation` VALUES (794447, 35726, 37873);
COMMIT;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `user_id` varchar(128) NOT NULL,
  `status` varchar(8) NOT NULL,
  `tell_phone` varchar(11) DEFAULT NULL,
  `zip_code` varchar(15) DEFAULT NULL,
  `address` text,
  `address_detail` text,
  `user_name` varchar(128) DEFAULT NULL,
  `admin_yn` varchar(1) NOT NULL DEFAULT 'N',
  `refresh_token` varchar(128) DEFAULT NULL,
  `auth_access_cnt` double DEFAULT '0',
  `auth_access_time` date DEFAULT NULL,
  `img_path` text,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
BEGIN;
INSERT INTO `user_detail` VALUES ('admin', '1', '', '', '서울 특별시 마포구 마포대로 38 2층 202호', NULL, 'admin', 'Y', NULL, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for web_ide_user
-- ----------------------------
DROP TABLE IF EXISTS `web_ide_user`;
CREATE TABLE `web_ide_user` (
  `user_id` varchar(128) NOT NULL,
  `org_name` varchar(128) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `use_yn` varchar(1) DEFAULT 'N',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`org_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



SET FOREIGN_KEY_CHECKS = 1;
