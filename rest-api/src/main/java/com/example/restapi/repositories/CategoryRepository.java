package com.example.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapi.domain.Category;

import io.swagger.annotations.Api;

/**
 * Category repository class for database interaction
 */
@Repository
@Api(tags = "Category Entity")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Getting category based on the provided category name
     * @param categoryName
     * @return
     */
    Category findByCategoryName(String categoryName);
}
