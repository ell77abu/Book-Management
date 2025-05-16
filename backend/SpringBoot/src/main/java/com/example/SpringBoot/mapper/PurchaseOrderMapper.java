package com.example.SpringBoot.mapper;

import com.example.SpringBoot.entity.PurchaseOrder;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface PurchaseOrderMapper {
    // 根据ID查询进货单
    @Select("SELECT * FROM purchase_order WHERE id = #{id}")
    PurchaseOrder findById(Long id);
    
    // 根据书籍ID查询进货单
    @Select("SELECT * FROM purchase_order WHERE book_id = #{bookId} AND status != 2")
    List<PurchaseOrder> findByBookId(Long bookId);
    
    // 根据状态查询进货单
    @Select("SELECT * FROM purchase_order WHERE status = #{status}")
    List<PurchaseOrder> findByStatus(Integer status);
    
    // 插入新进货单
    @Insert("INSERT INTO purchase_order(book_id, quantity, purchase_price, total_amount, status, purchased_by, created_at, updated_at, isbn, title, author, publisher) " +
            "VALUES(#{bookId}, #{quantity}, #{purchasePrice}, #{totalAmount}, #{status}, #{purchasedBy}, #{createdAt}, #{updatedAt}, #{isbn}, #{title}, #{author}, #{publisher})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(PurchaseOrder purchaseOrder);
    
    // 更新进货单状态
    @Update("UPDATE purchase_order SET status = #{status}, updated_at = #{updatedAt} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status, @Param("updatedAt") Date updatedAt);
    
    // 查询所有进货单
    @Select("SELECT po.*, b.isbn, b.title, b.author, b.publisher, b.retail_price " +
            "FROM purchase_order po " +
            "LEFT JOIN book b ON po.book_id = b.id " +
            "ORDER BY po.created_at DESC")
    List<PurchaseOrder> findAll();
    // 新增：更新进货单的bookId
    @Update("UPDATE purchase_order SET book_id = #{bookId} WHERE id = #{id}")
    int updateBookId(@Param("id") Long id, @Param("bookId") Long bookId);
}
