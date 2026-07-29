package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.MenuItem;
import com.foodnest.foodnest.repository.MenuItemRepository;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Save Menu Item
    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // Get All Menu Items
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Get Menu Item By Id
    public MenuItem getMenuItemById(int id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    // Update Menu Item
    public MenuItem updateMenuItem(int id, MenuItem menuItem) {

        MenuItem existingMenuItem = menuItemRepository.findById(id).orElse(null);

        if (existingMenuItem != null) {

            existingMenuItem.setFoodName(menuItem.getFoodName());
            existingMenuItem.setDescription(menuItem.getDescription());
            existingMenuItem.setPrice(menuItem.getPrice());
            existingMenuItem.setImage(menuItem.getImage());
            existingMenuItem.setStock(menuItem.getStock());
            existingMenuItem.setCategory(menuItem.getCategory());

            return menuItemRepository.save(existingMenuItem);
        }

        return null;
    }

    // Delete Menu Item
    public String deleteMenuItem(int id) {

        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return "Menu Item Deleted Successfully";
        }

        return "Menu Item Not Found";
    }
    public List<MenuItem> searchMenuItem(String foodName) {
        return menuItemRepository.findByFoodNameContainingIgnoreCase(foodName);
    }
    public List<MenuItem> getMenuItemsByCategory(int categoryId) {
        return menuItemRepository.findByCategoryId(categoryId);
    }
}
