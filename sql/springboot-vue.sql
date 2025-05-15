-- 设置字符集
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =========================
-- 表结构：用户 User 表
-- =========================
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `username` VARCHAR(255) NOT NULL UNIQUE COMMENT '用户名，唯一',
  `password` VARCHAR(255) NOT NULL COMMENT '密码（MD5加密）',
  `real_name` VARCHAR(255) DEFAULT NULL COMMENT '真实姓名',
  `employee_id` VARCHAR(255) NOT NULL UNIQUE COMMENT '工号，唯一',
  `gender` VARCHAR(10) DEFAULT NULL COMMENT '性别',
  `age` INT DEFAULT NULL COMMENT '年龄',
  `role` INT NOT NULL COMMENT '角色（0: 超级管理员, 1: 普通管理员）',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- =========================
-- 表结构：图书 Book 表
-- =========================
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `isbn` VARCHAR(255) NOT NULL UNIQUE COMMENT 'ISBN号，唯一',
  `title` VARCHAR(255) DEFAULT NULL COMMENT '书名',
  `author` VARCHAR(255) DEFAULT NULL COMMENT '作者',
  `publisher` VARCHAR(255) DEFAULT NULL COMMENT '出版社',
  `retail_price` DECIMAL(10,2) DEFAULT NULL COMMENT '零售价',
  `stock_quantity` INT DEFAULT 0 COMMENT '库存数量',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图书表';

-- =========================
-- 插入数据：图书 Book 表
-- =========================
INSERT INTO `book` (`isbn`, `title`, `retail_price`, `author`, `publisher`, `stock_quantity`)
VALUES
('12341541321', '十万个为什么', 15.00, '小王', '宁波大学出版社', 7),
('2312315132131', '五万个为什么', NULL, NULL, NULL, 3),
('25213121232', '一万个为什么', NULL, NULL, NULL, 5),
('3213123123', '操作系统', NULL, NULL, NULL, 8),
('345621212321', '伊索寓言', NULL, NULL, NULL, 9),
('54112312321', '格林童话', NULL, NULL, NULL, 1);
