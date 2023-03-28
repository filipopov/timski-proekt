package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Address;
import com.example.timskiproekt.domain.City;
import com.example.timskiproekt.domain.dto.AddressDto;
import com.example.timskiproekt.domain.exceptions.AddressNotFoundException;
import com.example.timskiproekt.domain.exceptions.CityNotFoundException;
import com.example.timskiproekt.repository.AddressRepository;
import com.example.timskiproekt.repository.CityRepository;
import com.example.timskiproekt.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    @Override
    public Address save(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return this.addressRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Address> findAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public void updateAddress(Long id, AddressDto addressDto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(AddressNotFoundException::new);
        City city = cityRepository.findByName(addressDto.getCity())
                .orElseThrow(CityNotFoundException::new);
        address.setCities(List.of(city));

        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        addressRepository.deleteAll();
    }

}
