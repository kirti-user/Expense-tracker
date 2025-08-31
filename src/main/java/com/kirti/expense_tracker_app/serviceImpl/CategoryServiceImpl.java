package com.kirti.expense_tracker_app.serviceImpl;

import com.kirti.expense_tracker_app.dto.CategoryDto;
import com.kirti.expense_tracker_app.entity.Category;
import com.kirti.expense_tracker_app.mapper.CategoryMapper;
import com.kirti.expense_tracker_app.repository.CategoryRepository;
import com.kirti.expense_tracker_app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // convert Category Dto to Category entity
        Category category = CategoryMapper.mapToCategory(categoryDto);

        // save category object into database table - categories
        Category savedCategory = categoryRepository.save(category);

        // convert savedCategory to CategoryDto
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found with id: " + categoryId));

        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        
        return List.of();
    }
}
