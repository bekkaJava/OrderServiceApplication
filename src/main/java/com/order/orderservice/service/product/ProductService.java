package com.order.orderservice.service.product;

import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.request.product.ProductAddRequest;
import com.order.orderservice.request.product.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long productId);

    void addProduct(ProductAddRequest productRequest);

    void deleteProductById(Long productId);

    ProductDTO updateProduct(Long productId, ProductUpdateRequest productUpdateRequest);
}
