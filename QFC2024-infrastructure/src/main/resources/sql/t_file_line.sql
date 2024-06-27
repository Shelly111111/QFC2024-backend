/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : qfc2024

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/06/2024 21:44:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_file_line
-- ----------------------------
DROP TABLE IF EXISTS `t_file_line`;
CREATE TABLE `t_file_line`  (
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_id` int(0) NOT NULL COMMENT '文件id',
  `version` int unsigned NOT NULL COMMENT '文件版本',
  `line` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '行内容',
  `decrypt_line` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '解密后的行内容',
  `line_index` bigint unsigned NOT NULL COMMENT '行下标',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_delete` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`uuid`) USING BTREE,
  INDEX `Index_file_id`(`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
