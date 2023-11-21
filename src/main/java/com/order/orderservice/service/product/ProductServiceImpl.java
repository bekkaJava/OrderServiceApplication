package com.order.orderservice.service.product;

import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.dto.product.ProductDTOMapper;
import com.order.orderservice.exception.CategoryNotFoundException;
import com.order.orderservice.exception.ProductNotFoundException;
import com.order.orderservice.model.Category;
import com.order.orderservice.model.Product;
import com.order.orderservice.repository.CategoryRepository;
import com.order.orderservice.repository.ProductRepository;
import com.order.orderservice.request.product.ProductAddRequest;
import com.order.orderservice.request.product.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ProductDTOMapper productDTOMapper;

    @Override
    public void addProduct(ProductAddRequest productRequest) {

        Category category = categoryRepository.findById(productRequest.getCategoryId()).
                orElseThrow(() -> new CategoryNotFoundException("Category with id %d not found",
                        productRequest.getCategoryId()));

        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        category.addProduct(product);
        categoryRepository.save(category);

    }


    @Override
    public void deleteProductById(Long productId) {

        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product with id %d not found", productId);
        }

        productRepository.deleteById(productId);

    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found", productId));


        if (nonNull(productUpdateRequest.getProductName()) &&
                !productUpdateRequest.getProductName().isBlank()) {
            product.setName(productUpdateRequest.getProductName());
        }

        if (nonNull(productUpdateRequest.getDescription()) &&
                !productUpdateRequest.getDescription().isBlank()) {
            product.setDescription(productUpdateRequest.getDescription());

        }

        if (nonNull(productUpdateRequest.getPrice())) {
            product.setPrice(productUpdateRequest.getPrice());

        }


        productRepository.save(product);

        return productDTOMapper.apply(product);
    }


    @Override
    public List<ProductDTO> findAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .toList();
    }

    @Override
    public ProductDTO findProductById(Long productId) {

        return productRepository.findById(productId)
                .map(productDTOMapper)
                .orElseThrow(() -> new ProductNotFoundException("Product with id %d not found", productId));
    }


}
