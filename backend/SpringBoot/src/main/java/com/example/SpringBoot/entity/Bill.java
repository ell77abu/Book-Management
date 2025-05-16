package com.example.SpringBoot.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    private Long id;                // 主键，自增
    private Integer type;           // 类型(0: 支出, 1: 收入)
    private BigDecimal amount;      // 金额
    private Long relatedId;         // 关联ID(进货ID或销售ID)
    private Integer recordType;     // 关联类型(0: 进货, 1: 销售)
    private Long createdBy;         // 外键，关联User表
    private Date createdAt;         // 创建时间
    private Date updatedAt;         // 更新时间
}
