package com.example.restapi.api.v1.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Category list data transfer object model
 */
@ApiModel(value = "Category List", description = "category list")
@Data
@AllArgsConstructor
public class CategoryListDTO {

    List<CategoryDTO> categories;
}
