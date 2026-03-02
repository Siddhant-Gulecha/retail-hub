package com.nexus.retail_engine.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "customers")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    // DATA

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String firstName;

    @Size(max = 100)
    private String lastName;


    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    @Column(length = 15)
    private String phoneNumber;



    // RELATIONSHIPS

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    // TIMESTAMPS

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
