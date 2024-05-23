package com.expensetracker.Service;

import com.expensetracker.Model.Category;
import com.expensetracker.Repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testGetAllCategories() {
        // Test getting all categories
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(categories, categoryService.getAllCategories());
    }

    @Test
    public void testGetCategoryById() {
        // Test getting a category by ID
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Optional<Category> retrievedCategory = categoryService.getCategoryById(categoryId);
        assertTrue(retrievedCategory.isPresent());
        assertEquals(categoryId, retrievedCategory.get().getId());
    }

    @Test
    public void testCreateCategory() {
        // Test creating a new category
        Category category = new Category();
        category.setName("Groceries");
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoryService.createCategory(category));
    }

    @Test
    public void testUpdateCategory() {
        // Test updating a category
        Long categoryId = 1L;
        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Old Name");

        Category updatedCategory = new Category();
        updatedCategory.setId(categoryId);
        updatedCategory.setName("New Name");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);

        Category result = categoryService.updateCategory(categoryId, updatedCategory);
        assertEquals("New Name", result.getName());
    }

    @Test
    public void testDeleteCategory() {
        // Test deleting a category
        Long categoryId = 1L;
        categoryService.deleteCategory(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    // Add more tests for error handling and edge cases

}
