package com.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Category;
import com.tienda.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Optional<Category>getCategoryById(Long id){
		return categoryRepository.findById(id);
	}
	
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        category.setName(categoryDetails.getName());
        category.setParentCategory(categoryDetails.getParentCategory());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
