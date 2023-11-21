package com.order.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "orderItems")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {
                                        MERGE,
                                        PERSIST,
                                        DETACH,
                                        REFRESH}
    )
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id") //uni-directional relation
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "order_date")
    private LocalDateTime orderDate;


}
