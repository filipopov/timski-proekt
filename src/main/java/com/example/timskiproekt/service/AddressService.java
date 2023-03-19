package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Address;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    Address findById(Long id);

    List<Address> findAll();
}
