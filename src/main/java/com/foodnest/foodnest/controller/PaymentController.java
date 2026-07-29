package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.entity.Payment;
import com.foodnest.foodnest.service.PaymentService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id:[0-9]+}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentService.getPaymentById(id);
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable int id,
                                 @RequestBody Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable int id) {
        return paymentService.deletePayment(id);
    }
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam Double amount) throws Exception {
        return ResponseEntity.ok(paymentService.createRazorpayOrder(amount));
    }
}
