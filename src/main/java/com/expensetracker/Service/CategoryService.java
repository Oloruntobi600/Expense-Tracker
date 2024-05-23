package com.expensetracker.Service;

import com.expensetracker.Model.Category;
import com.expensetracker.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            Category existingCategory = category.get();
            existingCategory.setName(categoryDetails.getName());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
