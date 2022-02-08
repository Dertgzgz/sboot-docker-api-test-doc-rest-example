package com.example.restapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.restapi.api.v1.mapper.CategoryMapper;
import com.example.restapi.api.v1.model.CategoryDTO;
import com.example.restapi.domain.Category;
import com.example.restapi.repositories.CategoryRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryControllerServiceTest {

	public static final String CATEGORY_NAME = "Fruits";
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeAll
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
	}

	@Test
	void getCategories() {

		// Given
		List<Category> categoryList = new ArrayList<Category>();

		Category one = new Category();
		Category two = new Category();

		categoryList.add(one);
		categoryList.add(two);

		// When
		when(categoryRepository.findAll()).thenReturn(categoryList);

		List<CategoryDTO> categoryDTOList = categoryService.getCategories();

		// Then
		assertEquals(categoryList.size(), categoryDTOList.size());
	}

	@Test
	void findByCategoryName() {

		// Given
		Category category = new Category();
		category.setCategoryId(1L);
		category.setCategoryName(CATEGORY_NAME);

		// When
		when(categoryRepository.findByCategoryName(CATEGORY_NAME)).thenReturn(category);

		CategoryDTO categoryDTO = categoryService.findByCategoryName(CATEGORY_NAME);

		// Then
		assertEquals(categoryDTO.getCategoryName(), CATEGORY_NAME);
	}
}