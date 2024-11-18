package com.wordpress.murtuzarahman.expensetrackerapi.service;

import com.wordpress.murtuzarahman.expensetrackerapi.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    void deleteCategory(String categoryId);
}
