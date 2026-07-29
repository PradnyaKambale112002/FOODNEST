package com.foodnest.foodnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodnest.foodnest.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}