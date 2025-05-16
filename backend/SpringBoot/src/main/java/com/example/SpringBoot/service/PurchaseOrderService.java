package com.example.SpringBoot.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.PurchaseOrder;

public interface PurchaseOrderService {
    // 创建进货单
    Result<PurchaseOrder> createPurchaseOrder(PurchaseOrder purchaseOrder, Long operatorId);
    
    // 进货付款
    Result<PurchaseOrder> payPurchaseOrder(Long id, Long operatorId);
    
    // 图书退货
    Result<PurchaseOrder> returnPurchaseOrder(Long id, Long operatorId);
    
    // 书籍入库
    Result<PurchaseOrder> stockInPurchaseOrder(Long id, BigDecimal retailPrice, Long operatorId);
    
    // 获取所有进货单
    Result<List<PurchaseOrder>> getAllPurchaseOrders();
    
    // 根据状态获取进货单
    Result<List<PurchaseOrder>> getPurchaseOrdersByStatus(Integer status);
}