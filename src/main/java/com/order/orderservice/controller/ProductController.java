package com.order.orderservice.controller;

import com.order.orderservice.dto.product.ProductDTO;
import com.order.orderservice.request.product.ProductAddRequest;
import com.order.orderservice.request.product.ProductUpdateRequest;
import com.order.orderservice.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long productId) {

        return ok(productService.findProductById(productId));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> findAllProduct() {

        return ok(productService.findAllProducts());

    }

    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody ProductAddRequest productUpdateRequest) {

        productService.addProduct(productUpdateRequest);

        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductUpdateRequest productUpdateRequest) {

        return new ResponseEntity<>(productService.updateProduct(productId, productUpdateRequest), ACCEPTED);
    }


    @DeleteMapping("{productId}")

    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {

        productService.deleteProductById(productId);

        return ResponseEntity.noContent().build();
    }
}
