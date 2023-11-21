package com.order.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "orders")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
                                                        cascade = {
                                                        MERGE,
                                                        PERSIST,
                                                        DETACH,
                                                        REFRESH})
    private List<Order> orders = new ArrayList<>();


    public void addOrder(Order order) {
        order.setCustomer(this);
        orders.add(order);
    }

}
