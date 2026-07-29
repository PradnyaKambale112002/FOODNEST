package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodnest.foodnest.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}