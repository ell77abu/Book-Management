package com.example.SpringBoot.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PurchaseOrder {
    private Long id;                // 主键，自增
    private Long bookId;            // 外键，关联Book表
    private Integer quantity;       // 进货数量
    private BigDecimal purchasePrice; // 进货价格
    private BigDecimal totalAmount;   // 总金额
    private Integer status;         // 状态：0-未付款，1-已付款，2-已退货，3-已入库
    private Long purchasedBy;        // 外键，关联User表
    private Date createdAt;         // 创建时间
    private Date updatedAt;         // 更新时间
    
    // 非数据库字段，用于创建订单时传递信息
    private String isbn;            // ISBN
    private String title;           // 书名
    private String author;          // 作者
    private String publisher;       // 出版社
    private BigDecimal retailPrice; // 零售价格（入库时设置）


}
