package com.example.timskiproekt.domain;

import com.example.timskiproekt.domain.enumerations.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdOn;
    @Enumerated(value = EnumType.STRING)
    private CartStatus status;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    public Cart(User user) {
        this.createdOn = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = CartStatus.CREATED;
    }
}
