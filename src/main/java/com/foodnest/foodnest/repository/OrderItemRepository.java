package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodnest.foodnest.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}