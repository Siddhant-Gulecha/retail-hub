package com.nexus.retail_engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nexus.retail_engine.entity.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    // DATA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(max=50)
    private String name;

    @Column
    @Size(max = 1000)
    private String description;

    @Column
    private Double price;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Currency currency;


    // RELATIONSHIPS

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @ToString.Exclude
    @JsonIgnoreProperties("products") // Prevents Seller from serializing its 'products' list here
    private Seller seller;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "orders_product_mapping",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders = new HashSet<>();


    // TIMESTAMPS

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
