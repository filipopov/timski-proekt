package com.example.domain;

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
    @GeneratedValue (strategy = GenerationType.UUID)
    private long number;
    private String name;
    @OneToMany (mappedBy = "code")
    private List<City> city;


}