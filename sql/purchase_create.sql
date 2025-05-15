DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE purchase_order (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '进货单ID，主键自增',
  book_id BIGINT NOT NULL COMMENT '书籍ID，外键关联book表',
  quantity INT NOT NULL COMMENT '进货数量',
  purchase_price DECIMAL(10,2) NOT NULL COMMENT '进货单价',
  total_amount DECIMAL(10,2) NOT NULL COMMENT '总金额 = 数量 × 进货单价',
  status INT NOT NULL DEFAULT 0 COMMENT '状态：0=未付款，1=已付款，2=已退货，3=已入库',
  purchased_by BIGINT NOT NULL COMMENT '操作进货的用户ID，外键关联user表',
  created_at DATETIME NOT NULL COMMENT '创建时间',
  updated_at DATETIME NOT NULL COMMENT '更新时间',
  FOREIGN KEY (book_id) REFERENCES book(id),
  FOREIGN KEY (purchased_by) REFERENCES user(id)
) COMMENT='进货订单表';