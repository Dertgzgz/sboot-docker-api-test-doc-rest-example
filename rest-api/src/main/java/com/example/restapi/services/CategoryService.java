package com.example.restapi.services;

import java.util.List;

import com.example.restapi.api.v1.model.CategoryDTO;

/**
 * Category Interface
 */
public interface CategoryService {

    List<CategoryDTO> getCategories();
    CategoryDTO findByCategoryName(String categoryName);
}
