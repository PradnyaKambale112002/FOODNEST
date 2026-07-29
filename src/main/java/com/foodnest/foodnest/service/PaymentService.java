package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.Payment;
import com.foodnest.foodnest.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import com.razorpay.RazorpayClient;
import com.razorpay.Order;
import org.json.JSONObject;


@Service
public class PaymentService {
	
	@Value("${razorpay.key.id}")
	private String keyId;

	@Value("${razorpay.key.secret}")
	private String keySecret;

    @Autowired
    private PaymentRepository paymentRepository;

    // Save Payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get Payment By Id
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }

    // Update Payment
    public Payment updatePayment(int id, Payment payment) {

        Payment existingPayment = paymentRepository.findById(id).orElse(null);

        if (existingPayment != null) {

            existingPayment.setOrder(payment.getOrder());
            existingPayment.setPaymentMethod(payment.getPaymentMethod());
            existingPayment.setPaymentStatus(payment.getPaymentStatus());
            existingPayment.setTransactionId(payment.getTransactionId());

            return paymentRepository.save(existingPayment);
        }

        return null;
    }

    // Delete Payment
    public String deletePayment(int id) {

        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return "Payment Deleted Successfully";
        }

        return "Payment Not Found";
    }
    public String createRazorpayOrder(double amount) throws Exception {

        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();

        options.put("amount", (int) (amount * 100));
        options.put("currency", "INR");
        options.put("receipt", "receipt_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        return order.toString();
    }
}