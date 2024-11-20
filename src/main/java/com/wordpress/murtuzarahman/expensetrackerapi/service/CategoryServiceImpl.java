package com.wordpress.murtuzarahman.expensetrackerapi.service;

import com.wordpress.murtuzarahman.expensetrackerapi.dto.CategoryDTO;
import com.wordpress.murtuzarahman.expensetrackerapi.dto.UserDTO;
import com.wordpress.murtuzarahman.expensetrackerapi.entity.CategoryEntity;
import com.wordpress.murtuzarahman.expensetrackerapi.entity.User;
import com.wordpress.murtuzarahman.expensetrackerapi.exceptions.ItemExistsException;
import com.wordpress.murtuzarahman.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.wordpress.murtuzarahman.expensetrackerapi.mappers.CategoryMapper;
import com.wordpress.murtuzarahman.expensetrackerapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> list = categoryRepository.findByUserId(userService.getLoggedInUser().getId());
        return list.stream().map(categoryEntity -> categoryMapper.mapToCategoryDTO(categoryEntity)).collect(Collectors.toList());
    }


    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        boolean isCategoryPresent = categoryRepository.existsByNameAndUserId(categoryDTO.getName(), userService.getLoggedInUser().getId());
        if (isCategoryPresent) {
            throw new ItemExistsException("Category is already present for the name "+categoryDTO.getName());
        }
        CategoryEntity newCategory = categoryMapper.mapToCategoryEntity(categoryDTO);
        newCategory.setCategoryId(UUID.randomUUID().toString());
        newCategory.setUser(userService.getLoggedInUser());
        newCategory = categoryRepository.save(newCategory);
        return categoryMapper.mapToCategoryDTO(newCategory);
    }


    @Override
    public void deleteCategory(String categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInUser().getId(), categoryId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category not found for the id "+categoryId);
        }
        categoryRepository.delete(optionalCategory.get());
    }


    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
