package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodnest.foodnest.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUser_IdOrderByIdDesc(int userId);
	@Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o")
	double getTotalRevenue();
}
