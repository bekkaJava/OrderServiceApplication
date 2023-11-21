package com.order.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "category")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = {
                    MERGE,
                    PERSIST,
                    REFRESH,
                    DETACH})
    @JoinColumn(name = "category_id")
    private Category category;


}
