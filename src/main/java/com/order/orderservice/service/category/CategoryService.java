package com.order.orderservice.service.category;

import com.order.orderservice.dto.category.CategoryDTO;
import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.model.Category;
import com.order.orderservice.request.category.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryDTO findCategoryById(Long categoryId);

    List<CategoryDTO> findAllCategories();

    void addCategory(CategoryRequest categoryAddRequest);

    void deleteCategoryById(Long categoryId);

    List<ProductDTO> findAllProductsByCategoryId(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryRequest categoryRequest);


}
