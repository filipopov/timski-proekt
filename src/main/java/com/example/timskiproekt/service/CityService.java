package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.City;

import java.util.List;

public interface CityService {

    City save(City city);

    List<City> saveAll(List<City> cities);

     City findById(Long id);

    List<City> findAll();
}
