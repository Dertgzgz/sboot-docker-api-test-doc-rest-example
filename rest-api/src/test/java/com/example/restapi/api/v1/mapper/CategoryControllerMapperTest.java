package com.example.restapi.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.restapi.api.v1.model.CategoryDTO;
import com.example.restapi.domain.Category;

class CategoryControllerMapperTest {

	private static final String DRIED = "Dried";
	private static final String FRUITS = "Fruits";
	private static final long L1 = 1L;
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Test
	void categoryToCategoryDTO() {

		// Given
		Category category = new Category();
		category.setCategoryName(FRUITS);
		category.setCategoryId(L1);

		// When
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

		// Then
		assertEquals(categoryDTO.getCategoryName(), FRUITS);

	}

	@Test
	void categoryDTOToCategory() {

		// Given
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryName(DRIED);

		// When
		Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

		// Then
		assertEquals(category.getCategoryName(), DRIED);

	}
}