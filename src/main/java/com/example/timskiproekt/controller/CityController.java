package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.City;
import com.example.timskiproekt.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        return ResponseEntity.ok(cityService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<City> createCity(@RequestParam String name) {
        return ResponseEntity.ok(this.cityService.save(new City(name)));
    }
}
