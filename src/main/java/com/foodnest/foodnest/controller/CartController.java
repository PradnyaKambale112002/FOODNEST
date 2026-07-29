package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.entity.Cart;
import com.foodnest.foodnest.service.CartService;
import com.foodnest.foodnest.dto.CartRequest;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add Item
    @PostMapping
    public Cart addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }
    // View All Cart Items
    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    // View Cart Item By Id
    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable int id) {
        return cartService.getCartById(id);
    }

    // Update Cart Item
    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable int id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }

    // Delete Cart Item
    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable int id) {
        return cartService.deleteCart(id);
    }
    @GetMapping("/my")
    public List<Cart> getMyCart() {
        return cartService.getMyCart();
    }
    @PutMapping("/{id}/increase")
    public Cart increaseQuantity(@PathVariable int id) {
        return cartService.increaseQuantity(id);
    }

    @PutMapping("/{id}/decrease")
    public Cart decreaseQuantity(@PathVariable int id) {
        return cartService.decreaseQuantity(id);
    }
}
