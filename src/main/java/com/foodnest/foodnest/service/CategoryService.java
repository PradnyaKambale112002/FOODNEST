package com.foodnest.foodnest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.entity.Category;
import com.foodnest.foodnest.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Save Category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get All Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category updateCategory(int id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        }

        return null;
    }
    public String deleteCategory(int id) {

        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category Deleted Successfully";
        }

        return "Category Not Found";
    } 
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
