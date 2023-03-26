package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Address;
import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;

import java.util.List;

public interface AddressService {

    Address save(Address address);

    Address findById(Long id);

    List<Address> findAll();

    User register(String us, String pw, String repeatPw, String name, String surname, Role role);
}
