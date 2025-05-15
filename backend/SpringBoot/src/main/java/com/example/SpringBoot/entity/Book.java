package com.example.SpringBoot.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Book {
    private Long id;                // 主键，自增
    private String isbn;            // ISBN
    private String title;           // 书名
    private String author;          // 作者
    private String publisher;       // 出版社
    private BigDecimal retailPrice; // 零售价
    private Integer stockQuantity;  // 库存数量
}
