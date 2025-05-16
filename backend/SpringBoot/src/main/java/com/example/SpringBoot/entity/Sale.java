package com.example.SpringBoot.entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Sale {
    private Long id;                 // 主键，自增
    private Long bookId;             // 外键，关联Book表
    private Integer quantity;           // 销售数量
    private BigDecimal salePrice;       // 销售价格
    private BigDecimal totalAmount;   // 总金额
    private Long soldBy;            // 外键，关联User表
    private Date createdAt;         // 创建时间
    private Date updatedAt;         // 更新时间
}
