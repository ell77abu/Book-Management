package com.example.SpringBoot.controller;

import com.example.SpringBoot.common.Result;
import com.example.SpringBoot.entity.PurchaseOrder;
import com.example.SpringBoot.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // 创建进货单
    @PostMapping("/create")
    public Result<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder, @RequestHeader("userId") Long operatorId) {
        return purchaseOrderService.createPurchaseOrder(purchaseOrder, operatorId);
    }
    
    // 进货付款
    @PostMapping("/pay/{id}")
    public Result<PurchaseOrder> payPurchaseOrder(@PathVariable Long id, @RequestHeader("userId") Long operatorId) {
        return purchaseOrderService.payPurchaseOrder(id, operatorId);
    }
    
    // 图书退货
    @PostMapping("/return/{id}")
    public Result<PurchaseOrder> returnPurchaseOrder(@PathVariable Long id, @RequestHeader("userId") Long operatorId) {
        return purchaseOrderService.returnPurchaseOrder(id, operatorId);
    }
    
    // 书籍入库
    @PostMapping("/stockin/{id}")
    public Result<PurchaseOrder> stockInPurchaseOrder(
            @PathVariable Long id, 
            @RequestParam BigDecimal retailPrice, 
            @RequestHeader("userId") Long operatorId) {
        return purchaseOrderService.stockInPurchaseOrder(id, retailPrice, operatorId);
    }
    
    // 获取所有进货单
    @GetMapping("/all")
    public Result<List<PurchaseOrder>> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }
    
    // 根据状态获取进货单
    @GetMapping("/status/{status}")
    public Result<List<PurchaseOrder>> getPurchaseOrdersByStatus(@PathVariable Integer status) {
        return purchaseOrderService.getPurchaseOrdersByStatus(status);
    }
}
