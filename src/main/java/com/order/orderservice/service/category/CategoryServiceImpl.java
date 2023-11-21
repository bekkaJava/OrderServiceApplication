package com.order.orderservice.service.category;

import com.order.orderservice.dto.category.CategoryDTO;
import com.order.orderservice.dto.category.CategoryDTOMapper;
import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.dto.product.ProductDTOMapper;
import com.order.orderservice.exception.CategoryNotFoundException;
import com.order.orderservice.model.Category;
import com.order.orderservice.model.Product;
import com.order.orderservice.repository.CategoryRepository;
import com.order.orderservice.request.category.CategoryRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryDTOMapper categoryDTOMapper;

    private final ProductDTOMapper productDTOMapper;


    @Override
    public void addCategory(CategoryRequest categoryRequest) {

        Category category = Category.builder()
                .name(categoryRequest.getCategoryName())
                .description(categoryRequest.getCategoryDescription())
                .build();

        categoryRepository.save(category);
    }


    @Override
    public void deleteCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found", categoryId));

        List<Product> products = category.getProducts();

        products.forEach(product -> product.setCategory(null));

        categoryRepository.deleteById(categoryId);


    }

    @Override
    public List<ProductDTO> findAllProductsByCategoryId(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .map(category -> category.getProducts()
                        .stream()
                        .map(productDTOMapper).toList())
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found", categoryId));
    }


    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryRequest categoryAddRequest) {


        Category updateCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found", categoryId));

        if (nonNull(categoryAddRequest.getCategoryName()) &&
                !categoryAddRequest.getCategoryName().isBlank()) {

            updateCategory.setName(categoryAddRequest.getCategoryName());
        }

        if (nonNull(categoryAddRequest.getCategoryDescription()) &&
                !categoryAddRequest.getCategoryDescription().isBlank()) {

            updateCategory.setDescription(categoryAddRequest.getCategoryDescription());
        }


        categoryRepository.save(updateCategory);

        return categoryDTOMapper.apply(updateCategory);
    }

    @Override
    public CategoryDTO findCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .map(categoryDTOMapper)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found", categoryId));

    }

    @Override
    public List<CategoryDTO> findAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryDTOMapper)
                .toList();
    }


}
