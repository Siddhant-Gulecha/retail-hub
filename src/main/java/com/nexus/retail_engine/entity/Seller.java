package com.nexus.retail_engine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Seller {

    // DATA

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, length = 100)
    private String sellerName; // company name

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    @Column(length = 15)
    private String phoneNumber;

    @NotBlank(message = "GSTIN is mandatory")
    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
            message = "Invalid GSTIN format"
    )
    @Column(name = "gstin", unique = true, length = 15)
    private String gstin;

    // RELATIONSHIPS

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "seller", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products;

    // TIMESTAMPS

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
