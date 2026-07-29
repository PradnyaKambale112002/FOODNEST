package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.Cart;
import com.foodnest.foodnest.repository.CartRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.foodnest.foodnest.entity.MenuItem;
import com.foodnest.foodnest.entity.User;
import com.foodnest.foodnest.repository.MenuItemRepository;
import com.foodnest.foodnest.repository.UserRepository;
import com.foodnest.foodnest.dto.CartRequest;



@Service
public class CartService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;
	
    @Autowired
    private CartRepository cartRepository;

    // Add Item to Cart
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // View All Cart Items
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Get Cart Item By Id
    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    // Update Cart Item
    public Cart updateCart(int id, Cart cart) {

        Cart existingCart = cartRepository.findById(id).orElse(null);

        if (existingCart != null) {
        	existingCart.setUser(cart.getUser());
        	existingCart.setMenuItem(cart.getMenuItem());
        	existingCart.setQuantity(cart.getQuantity());

            return cartRepository.save(existingCart);
        }

        return null;
    }

    // Remove Cart Item
    public String deleteCart(int id) {

        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return "Cart Item Deleted Successfully";
        }

        return "Cart Item Not Found";
    }
    public Cart addToCart(CartRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Authentication: " + authentication);

        String email = authentication.getName();
        System.out.println("Email: " + email);

        User user = userRepository.findByEmail(email).orElse(null);
        System.out.println("User: " + user);

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElse(null);
        System.out.println("Menu Item: " + menuItem);

        if (user == null || menuItem == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setMenuItem(menuItem);
        cart.setQuantity(request.getQuantity());

        System.out.println("Saving cart...");

        Cart savedCart = cartRepository.save(cart);

        System.out.println("Saved Cart ID: " + savedCart.getId());

        return savedCart;
    }
    public List<Cart> getMyCart() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return List.of();
        }

        return cartRepository.findByUser_Id(user.getId());
    }
    public Cart increaseQuantity(int cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
            return cartRepository.save(cart);
        }

        return null;
    }
    public Cart decreaseQuantity(int cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != null) {

            if (cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                return cartRepository.save(cart);
            } else {
                cartRepository.delete(cart);
            }
        }

        return null;
    }
}
