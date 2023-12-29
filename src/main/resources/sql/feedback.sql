/*
 Navicat Premium Data Transfer

 Source Server         : ST
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : 1.117.58.26:3306
 Source Schema         : smart_toilet

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 29/12/2023 16:56:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `toilet_id` int(0) NOT NULL,
  `room_id` int(0) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `status` int(0) NULL DEFAULT 0,
  `room_db_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
