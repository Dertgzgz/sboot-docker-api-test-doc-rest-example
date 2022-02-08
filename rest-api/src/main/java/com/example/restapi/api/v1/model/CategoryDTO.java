package com.example.restapi.api.v1.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Category data transfer object model
 */
@ApiModel(value = "Category", description = "category")
@Data
public class CategoryDTO {

    @ApiModelProperty(value = "Category Name", required = true)
    @NotNull(message = "NotNull.categoryDTO.description")
    @Size(min = 1, max = 255)
    private String categoryName;

}
