package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.OrderItem;
import com.foodnest.foodnest.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Save Order Item
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Get All Order Items
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get Order Item By Id
    public OrderItem getOrderItemById(int id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    // Update Order Item
    public OrderItem updateOrderItem(int id, OrderItem orderItem) {

        OrderItem existingOrderItem = orderItemRepository.findById(id).orElse(null);

        if (existingOrderItem != null) {
            existingOrderItem.setOrder(orderItem.getOrder());
            existingOrderItem.setMenuItem(orderItem.getMenuItem());
            existingOrderItem.setQuantity(orderItem.getQuantity());
            existingOrderItem.setPrice(orderItem.getPrice());

            return orderItemRepository.save(existingOrderItem);
        }

        return null;
    }

    // Delete Order Item
    public String deleteOrderItem(int id) {

        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return "Order Item Deleted Successfully";
        }

        return "Order Item Not Found";
    }
}