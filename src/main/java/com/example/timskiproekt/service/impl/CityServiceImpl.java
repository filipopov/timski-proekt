package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.City;
import com.example.timskiproekt.repository.CityRepository;
import com.example.timskiproekt.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public List<City> saveAll(List<City> cities) {
        return this.cityRepository.saveAll(cities);
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }
}
