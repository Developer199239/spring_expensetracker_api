package com.wordpress.murtuzarahman.expensetrackerapi.mappers;

import com.wordpress.murtuzarahman.expensetrackerapi.dto.CategoryDTO;
import com.wordpress.murtuzarahman.expensetrackerapi.entity.CategoryEntity;
import com.wordpress.murtuzarahman.expensetrackerapi.io.CategoryRequest;
import com.wordpress.murtuzarahman.expensetrackerapi.io.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryEntity mapToCategoryEntity(CategoryDTO categoryDTO);
    CategoryDTO mapToCategoryDTO(CategoryEntity entity);
    @Mapping(target = "categoryIcon", source = "icon")
    CategoryDTO mapToCategoryDTO(CategoryRequest request);
    CategoryResponse mapToCategoryResponse(CategoryDTO categoryDTO);
}
