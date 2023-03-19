package com.example.timskiproekt.domain;

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
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long code;
   private String name;

   public City(String name) {
       this.name = name;
   }
}
