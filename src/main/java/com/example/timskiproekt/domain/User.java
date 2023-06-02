package com.example.timskiproekt.domain;

import com.example.timskiproekt.domain.annotation.ValidPhone;
import com.example.timskiproekt.domain.enumerations.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "shop_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    @ValidPhone
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    @OneToOne
    private Cart cart;

    private String address;

    public User(String firstName, String lastName, String username, String email,
                String password, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userRole = Role.USER;
    }


    public User(String firstName, String lastName, String email, String username,
                String password, String phoneNumber, Role userRole, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.address = address;
    }
}
