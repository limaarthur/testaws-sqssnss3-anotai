package com.ignitec.teste_anotai.controllers;

import com.ignitec.teste_anotai.domain.category.Category;
import com.ignitec.teste_anotai.domain.category.CategoryDTO;
import com.ignitec.teste_anotai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = this.categoryService.create(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = this.categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = this.categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
