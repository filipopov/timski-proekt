package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Address;
import com.example.timskiproekt.domain.dto.AddressDto;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    List<Address> saveAll(List<Address> addresses);

    Address findById(Long id);

    List<Address> findAll();

    void updateAddress(Long id, AddressDto addressDto);

    void deleteAddress(Long id);

    void deleteAll();

}
