package com.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private long code;
    private String name;
}
