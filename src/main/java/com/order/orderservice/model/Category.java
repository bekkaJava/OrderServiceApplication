package com.order.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "products")
public class Category {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
                                        MERGE,
                                        PERSIST,
                                        REFRESH,
                                        DETACH},
                                        mappedBy = "category")
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        product.setCategory(this);
        products.add(product);
    }


}
