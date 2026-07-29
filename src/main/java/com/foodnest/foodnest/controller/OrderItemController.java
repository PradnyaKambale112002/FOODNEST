package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.entity.OrderItem;
import com.foodnest.foodnest.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable int id) {
        return orderItemService.getOrderItemById(id);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable int id,
                                     @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderItem(@PathVariable int id) {
        return orderItemService.deleteOrderItem(id);
    }
}