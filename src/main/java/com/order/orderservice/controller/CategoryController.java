package com.order.orderservice.controller;

import com.order.orderservice.dto.category.CategoryDTO;
import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.request.category.CategoryRequest;
import com.order.orderservice.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v2/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long categoryId) {

        return ok(categoryService.findCategoryById(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> findAllCategory() {

        return ok(categoryService.findAllCategories());

    }

    @GetMapping("/findProductsByCategoryId/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findProductsByCategoryId(@PathVariable Long categoryId) {

        return ok(categoryService.findAllProductsByCategoryId(categoryId));
    }


    @PostMapping("/")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryAddRequest) {

        categoryService.addCategory(categoryAddRequest);

        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
                                                      @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryRequest), ACCEPTED);
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId) {

        categoryService.deleteCategoryById(categoryId);

        return noContent().build();
    }
}
