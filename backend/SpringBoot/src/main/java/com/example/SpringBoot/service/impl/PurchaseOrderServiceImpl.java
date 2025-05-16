package com.example.SpringBoot.service.impl;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.Book;
import com.example.SpringBoot.entity.PurchaseOrder;
import com.example.SpringBoot.entity.User;
import com.example.SpringBoot.mapper.BookMapper;
import com.example.SpringBoot.mapper.PurchaseOrderMapper;
import com.example.SpringBoot.mapper.UserMapper;
import com.example.SpringBoot.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    
    @Autowired
    private BookMapper bookMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    @Transactional
    public Result<PurchaseOrder> createPurchaseOrder(PurchaseOrder purchaseOrder, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("3001", "操作者不存在");
        }

        // 设置基本信息
        purchaseOrder.setPurchasedBy(operatorId);
        purchaseOrder.setStatus(0); // 未付款
        Date now = new Date();
        purchaseOrder.setCreatedAt(now);
        purchaseOrder.setUpdatedAt(now);

        // 计算总金额
        BigDecimal totalAmount = purchaseOrder.getPurchasePrice().multiply(new BigDecimal(purchaseOrder.getQuantity()));
        purchaseOrder.setTotalAmount(totalAmount);

        // 检查书籍是否存在
        Book existingBook = bookMapper.findByIsbn(purchaseOrder.getIsbn());

        if (existingBook != null) {
            // 书籍已存在，直接创建进货单
            purchaseOrder.setBookId(existingBook.getId());
        } else {
            // 书籍不存在，bookId留空，书籍信息暂存进进货单冗余字段
            purchaseOrder.setBookId(null);
        }
        purchaseOrderMapper.insert(purchaseOrder);
        return Result.success(purchaseOrder);
    }

    @Override
    @Transactional
    public Result<PurchaseOrder> payPurchaseOrder(Long id, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("3002", "操作者不存在");
        }

        // 检查进货单是否存在
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findById(id);
        if (purchaseOrder == null) {
            return Result.error("3003", "进货单不存在");
        }

        // 检查状态是否为未付款
        if (purchaseOrder.getStatus() != 0) {
            return Result.error("3004", "只有未付款的进货单才能付款");
        }

        // 如果bookId为空，说明是新书，需要插入Book表
        if (purchaseOrder.getBookId() == null) {
            Book newBook = new Book();
            newBook.setIsbn(purchaseOrder.getIsbn());
            newBook.setTitle(purchaseOrder.getTitle());
            newBook.setAuthor(purchaseOrder.getAuthor());
            newBook.setPublisher(purchaseOrder.getPublisher());
            newBook.setStockQuantity(0); // 初始库存为0
            // 零售价入库时再设置
            bookMapper.insert(newBook);
            // 回写bookId到进货单
            purchaseOrder.setBookId(newBook.getId());
            // 这里需要更新进货单的bookId
            purchaseOrderMapper.updateBookId(purchaseOrder.getId(), newBook.getId());
        }

        // 更新状态为已付款
        purchaseOrderMapper.updateStatus(id, 1, new Date());

        // 返回更新后的进货单
        return Result.success(purchaseOrderMapper.findById(id));
    }
    
    @Override
    public Result<PurchaseOrder> returnPurchaseOrder(Long id, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("3005", "操作者不存在");
        }
        
        // 检查进货单是否存在
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findById(id);
        if (purchaseOrder == null) {
            return Result.error("3006", "进货单不存在");
        }
        
        // 检查状态是否为未付款
        if (purchaseOrder.getStatus() != 0) {
            return Result.error("3007", "只有未付款的进货单才能退货");
        }
        
        // 更新状态为已退货
        purchaseOrderMapper.updateStatus(id, 2, new Date());
        
        // 返回更新后的进货单
        return Result.success(purchaseOrderMapper.findById(id));
    }
    
    @Override
    @Transactional
    public Result<PurchaseOrder> stockInPurchaseOrder(Long id, BigDecimal retailPrice, Long operatorId) {
        // 检查操作者是否存在
        User operator = userMapper.findById(operatorId);
        if (operator == null) {
            return Result.error("3008", "操作者不存在");
        }
        
        // 检查进货单是否存在
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findById(id);
        if (purchaseOrder == null) {
            return Result.error("3009", "进货单不存在");
        }
        
        // 检查状态是否为已付款
        if (purchaseOrder.getStatus() != 1) {
            return Result.error("3010", "只有已付款的进货单才能入库");
        }
        
        // 获取对应的书籍
        Book book = bookMapper.findById(purchaseOrder.getBookId());
        if (book == null) {
            return Result.error("3011", "书籍不存在");
        }
        
        // 更新书籍信息
        book.setRetailPrice(retailPrice);
        book.setStockQuantity(book.getStockQuantity() + purchaseOrder.getQuantity());
        bookMapper.update(book);
        
        // 更新进货单状态为已入库
        purchaseOrderMapper.updateStatus(id, 3, new Date());
        
        // 返回更新后的进货单
        return Result.success(purchaseOrderMapper.findById(id));
    }
    
    @Override
    public Result<List<PurchaseOrder>> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.findAll();
        return Result.success(purchaseOrders);
    }
    
    @Override
    public Result<List<PurchaseOrder>> getPurchaseOrdersByStatus(Integer status) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.findByStatus(status);
        return Result.success(purchaseOrders);
    }
}