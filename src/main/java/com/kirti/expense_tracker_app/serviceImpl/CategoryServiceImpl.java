package com.kirti.expense_tracker_app.serviceImpl;

import com.kirti.expense_tracker_app.dto.CategoryDto;
import com.kirti.expense_tracker_app.entity.Category;
import com.kirti.expense_tracker_app.mapper.CategoryMapper;
import com.kirti.expense_tracker_app.repository.CategoryRepository;
import com.kirti.expense_tracker_app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((category) -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {

        // get category entity from the database by category id
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found with id; " + categoryId));

        // update the category entity object and save  to database table
        category.setName(categoryDto.name());
        Category updatedCategory = categoryRepository.save(category); // perform update operation
        return CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(long categoryId) {

        // check if a category with given id is exists or not
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found with id: " + categoryId));

        categoryRepository.delete(category);
    }
}
