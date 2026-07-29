package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodnest.foodnest.entity.MenuItem;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByFoodNameContainingIgnoreCase(String foodName);
    List<MenuItem> findByCategoryId(int categoryId);
}
