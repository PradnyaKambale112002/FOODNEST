package com.foodnest.foodnest.controller;

import java.util.List;
import com.foodnest.foodnest.dto.OrderStatusDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.entity.Order;
import com.foodnest.foodnest.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create Order
    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // Get All Orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get Order By Id
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    // Update Order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id,
                             @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }
    @PostMapping("/place")
    public Order placeOrder() {
        return orderService.placeOrder();
    }
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable int id,
                                   @RequestBody OrderStatusDTO dto) {
        return orderService.updateOrderStatus(id, dto.getStatus());
    }
    @GetMapping("/my")
    public List<Order> getMyOrders() {
        return orderService.getMyOrders();
    }
}