package com.foodnest.foodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodnest.foodnest.entity.MenuItem;
import com.foodnest.foodnest.service.MenuItemService;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    // Save Menu Item
    @PostMapping
    public MenuItem saveMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.saveMenuItem(menuItem);
    }

    // Get All Menu Items
    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    // Get Menu Item By Id
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable int id) {
        return menuItemService.getMenuItemById(id);
    }

    // Update Menu Item
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable int id,
                                   @RequestBody MenuItem menuItem) {
        return menuItemService.updateMenuItem(id, menuItem);
    }

    // Delete Menu Item
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable int id) {
        return menuItemService.deleteMenuItem(id);
    }
    @GetMapping("/search")
    public List<MenuItem> searchMenuItem(@RequestParam String foodName) {
        return menuItemService.searchMenuItem(foodName);
    }
    @GetMapping("/category/{categoryId}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable int categoryId) {
        return menuItemService.getMenuItemsByCategory(categoryId);
    }
}
