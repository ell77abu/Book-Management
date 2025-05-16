DROP TABLE IF EXISTS `purchase_order`;

CREATE TABLE `purchase_order` (
    `id` BIGINT AUTO_INCREMENT COMMENT '主键，自增',
    `book_id` BIGINT COMMENT '外键，关联Book表',
    `isbn` VARCHAR(30) COMMENT 'ISBN（冗余字段）',
    `title` VARCHAR(255) COMMENT '书名（冗余字段）',
    `author` VARCHAR(100) COMMENT '作者（冗余字段）',
    `publisher` VARCHAR(100) COMMENT '出版社（冗余字段）',
    `retail_price` DECIMAL(10, 2) COMMENT '零售价格（冗余字段）',

    `quantity` INT NOT NULL COMMENT '进货数量',
    `purchase_price` DECIMAL(10, 2) NOT NULL COMMENT '进货价格',
    `total_amount` DECIMAL(10, 2) NOT NULL COMMENT '总金额',
    `status` TINYINT NOT NULL COMMENT '状态：0-未付款，1-已付款，2-已退货，3-已入库',
    `purchased_by` BIGINT NOT NULL COMMENT '外键，关联User表',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    PRIMARY KEY (`id`)
) COMMENT='进货订单表（包含图书信息冗余字段）';
