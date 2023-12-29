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

 Date: 29/12/2023 16:56:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for toilet_history
-- ----------------------------
DROP TABLE IF EXISTS `toilet_history`;
CREATE TABLE `toilet_history`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `temperature` float NULL DEFAULT NULL,
  `humidity` float NULL DEFAULT NULL,
  `air_status` float NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `toilet_id` int(0) NULL DEFAULT NULL,
  `fan_status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68435 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
