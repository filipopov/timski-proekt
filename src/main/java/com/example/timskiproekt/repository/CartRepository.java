package com.example.timskiproekt.repository;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndStatus(User user, CartStatus status);
}
