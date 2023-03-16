package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.repository.CityRepository;
import com.example.timskiproekt.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
}
