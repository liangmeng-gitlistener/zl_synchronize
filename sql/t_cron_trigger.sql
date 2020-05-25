SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cast_output
-- ----------------------------
DROP TABLE IF EXISTS `cast_output`;
CREATE TABLE `cast_output`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '铸轧产量表id',
  `alloy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合金分类',
  `weight` double(50, 0) NOT NULL COMMENT '单月单合金产量（T）',
  `total_weight` double(50, 0) NOT NULL COMMENT '月总产量',
  `year` int(10) NULL DEFAULT NULL COMMENT '当前产量所属年份',
  `month` int(10) NULL DEFAULT NULL COMMENT '当前产量所属月份',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cold_roll_output
-- ----------------------------
DROP TABLE IF EXISTS `cold_roll_output`;
CREATE TABLE `cold_roll_output`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '冷轧产量表id',
  `alloy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合金分类',
  `weight` double(50, 0) NOT NULL COMMENT '单月单合金产量（T）',
  `total_weight` double(50, 0) NOT NULL COMMENT '月总产量',
  `year` int(10) NULL DEFAULT NULL COMMENT '当前产量所属年份',
  `month` int(10) NULL DEFAULT NULL COMMENT '当前产量所属月份',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for failure_reporting
-- ----------------------------
DROP TABLE IF EXISTS `failure_reporting`;
CREATE TABLE `failure_reporting`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '月退货折线图主键',
  `finished` int(50) NOT NULL COMMENT '月度成品量',
  `refund` int(50) NOT NULL COMMENT '月度退货量',
  `year` int(5) NULL DEFAULT NULL COMMENT '当前数据所属年份',
  `month` int(5) NULL DEFAULT NULL COMMENT '当前数据所属月份',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '数据录入或最后一次修改时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '冗余描述信息，用于扩展',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_schedule
-- ----------------------------
DROP TABLE IF EXISTS `order_schedule`;
CREATE TABLE `order_schedule`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '订单进度id',
  `process_product_order` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程生产订单',
  `product_order_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生产订单编号',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `customer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `alloy_state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合金状态',
  `plan_volume` int(20) NOT NULL COMMENT '本次订单计划卷数',
  `completed_volume` int(20) NOT NULL COMMENT '已完成卷',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `process_product_order`(`process_product_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_cron_trigger
-- ----------------------------
INSERT INTO `t_cron_trigger` VALUES (1, '0/10 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.MyTest', '2020-04-12 23:07:20', NULL, '2020-04-12 23:19:40');
INSERT INTO `t_cron_trigger` VALUES (2, '0/11 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.CastOutputTask', '2020-04-28 22:00:32', NULL, '2020-04-28 22:00:42');
INSERT INTO `t_cron_trigger` VALUES (3, '0/12 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.OrderProgressTask', '2020-04-28 22:00:32', NULL, '2020-04-27 04:51:24');
INSERT INTO `t_cron_trigger` VALUES (4, '0/13 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.ColdRollOutputTask', '2020-04-28 22:00:32', NULL, '2020-04-27 04:51:15');
INSERT INTO `t_cron_trigger` VALUES (5, '0/14 * * * * ?', '2020-04-09 13:40:14', '2020-04-09 13:40:14', 0, 'cn.cncommdata.runnable.WIPSummaryTask', '2020-04-28 22:00:32', NULL, '2020-04-27 04:51:19');


-- ----------------------------
-- Table structure for wip_summary
-- ----------------------------
DROP TABLE IF EXISTS `wip_summary`;
CREATE TABLE `wip_summary`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '在制品汇总表主键',
  `wip_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '在制品名称',
  `volume` int(50) NULL DEFAULT NULL COMMENT '在制品卷数汇总',
  `weight` double(50, 0) NULL DEFAULT NULL COMMENT '在制品重量(T)',
  `extra` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
