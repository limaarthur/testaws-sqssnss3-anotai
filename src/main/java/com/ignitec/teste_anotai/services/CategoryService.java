package com.ignitec.teste_anotai.services;

import com.ignitec.teste_anotai.domain.category.Category;
import com.ignitec.teste_anotai.domain.category.CategoryDTO;
import com.ignitec.teste_anotai.domain.category.exceptions.CategoryNotFoundException;
import com.ignitec.teste_anotai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());
        this.categoryRepository.save(category);
        return category;
    }

    public void delete(String id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.categoryRepository.delete(category);
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }
}
