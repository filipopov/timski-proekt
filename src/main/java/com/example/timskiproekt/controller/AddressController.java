package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.Address;
import com.example.timskiproekt.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAddresses(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Address> createCity(@RequestParam String name) {
        return ResponseEntity.ok(this.addressService.save(new Address(name)));
    }
}
