package com.order.orderservice.dto.category;

import com.order.orderservice.model.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {

        return new CategoryDTO(category.getName());
    }
}
