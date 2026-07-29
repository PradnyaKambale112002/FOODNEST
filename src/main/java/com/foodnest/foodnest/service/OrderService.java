package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodnest.foodnest.entity.Cart;
import com.foodnest.foodnest.entity.OrderItem;
import com.foodnest.foodnest.repository.CartRepository;
import com.foodnest.foodnest.repository.OrderItemRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.foodnest.foodnest.repository.UserRepository;
import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.entity.Order;
import com.foodnest.foodnest.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private UserRepository userRepository;

	public List<Order> getMyOrders() {

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    String email = authentication.getName();

	    User user = userRepository.findByEmail(email).orElse(null);

	    if (user == null) {
	        return List.of();
	    }

	    return orderRepository.findByUser_IdOrderByIdDesc(user.getId());
	}
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Save Order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get Order By Id
    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Update Order
    public Order updateOrder(int id, Order order) {

        Order existingOrder = orderRepository.findById(id).orElse(null);

        if (existingOrder != null) {
            existingOrder.setUser(order.getUser());
            existingOrder.setTotalAmount(order.getTotalAmount());
            existingOrder.setStatus(order.getStatus());

            return orderRepository.save(existingOrder);
        }

        return null;
    }

    // Delete Order
    public String deleteOrder(int id) {

        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "Order Deleted Successfully";
        }

        return "Order Not Found";
    }
    public Order placeOrder() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        List<Cart> cartItems = cartRepository.findByUser_Id(user.getId());

        if (cartItems.isEmpty()) {
            return null;
        }

        // Create a new order
        Order order = new Order();
        order.setUser(cartItems.get(0).getUser());
        order.setStatus("PLACED");

        double totalAmount = 0;

        // Save order first
        order = orderRepository.save(order);

        // Create order items
        for (Cart cart : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(cart.getMenuItem());
            orderItem.setQuantity(cart.getQuantity());

            double price = cart.getMenuItem().getPrice();
            orderItem.setPrice(price);

            totalAmount += price * cart.getQuantity();

            orderItemRepository.save(orderItem);
        }

        // Update total amount
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        // Clear cart
        cartRepository.deleteAll(cartItems);

        return order;
    }
    public Order updateOrderStatus(int id, String status) {

        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        order.setStatus(status);

        return orderRepository.save(order);
    }
}