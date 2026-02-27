package com.nexus.retail_engine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
public class Address {

    // DATA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address line is required")
    @Size(max = 255)
    @Column(nullable = false)
    private String addressLine;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[A-Z0-9 ]{3,10}$", message = "Invalid pincode format")
    @Column(nullable = false, length = 10)
    private String pincode;


    // RELATIONSHIPS

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") // Optional: only if this is a Customer address
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")   // Optional: only if this is a Seller address
    private Seller seller;

    // TIMESTAMPS

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}