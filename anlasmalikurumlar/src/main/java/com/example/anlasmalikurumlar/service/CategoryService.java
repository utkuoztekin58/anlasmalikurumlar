package com.example.anlasmalikurumlar.service;

import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.repository.CategoryRepository;
import com.example.anlasmalikurumlar.repository.CompaniesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CompaniesRepository companiesRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CompaniesRepository companiesRepository) {
        this.categoryRepository = categoryRepository;
        this.companiesRepository = companiesRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        try {
            return categoryRepository.findById(id).orElse(null);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }


    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Category updatedCategory) {

        Optional<Category> existingCategory = categoryRepository.findById(updatedCategory.getId());

        if (existingCategory.isPresent()) {

            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());


            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found");
        }
    }


    public boolean isCategoryAssociatedWithCompanies(Long categoryId) {

    return companiesRepository.existsByCategoryId(categoryId);

    }
}
