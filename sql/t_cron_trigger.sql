SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cron_trigger
-- ----------------------------
DROP TABLE IF EXISTS `t_cron_trigger`;
CREATE TABLE `t_cron_trigger`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `cron` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'cron表达式',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) NULL DEFAULT 0 COMMENT '作废状态 0-否 1-是',
  `class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类的全路径',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '任务运行开始时间',
  `stop_time` datetime(0) NULL DEFAULT NULL COMMENT '任务停止运行时间',
  `last_run_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次任务执行的完成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cron_trigger
-- ----------------------------
INSERT INTO `t_cron_trigger` VALUES (1, '0/10 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.MyTest', '2020-04-12 23:07:20', NULL, '2020-04-12 23:19:40');

SET FOREIGN_KEY_CHECKS = 1;
