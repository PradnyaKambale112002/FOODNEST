package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodnest.foodnest.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
