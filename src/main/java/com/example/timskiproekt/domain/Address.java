package com.example.timskiproekt.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;

    @OneToMany
    private List<City> cities;

    public Address(String name) {
        this.name = name;
    }
}
