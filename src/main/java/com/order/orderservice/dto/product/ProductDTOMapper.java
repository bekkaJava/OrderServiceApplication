package com.order.orderservice.dto.product;

import com.order.orderservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {

        return new ProductDTO(product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getName());
    }
}
