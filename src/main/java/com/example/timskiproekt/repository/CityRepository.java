package com.example.timskiproekt.repository;

import com.example.timskiproekt.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
