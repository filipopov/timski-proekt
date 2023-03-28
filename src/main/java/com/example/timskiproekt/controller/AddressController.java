package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.Address;
import com.example.timskiproekt.domain.dto.AddressDto;
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
    public ResponseEntity<Address> createAddress(@RequestParam String name, String city) {
        return ResponseEntity.ok(this.addressService.save(new Address(name, city)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Long id,
                                              @RequestParam AddressDto addressDto){
        addressService.updateAddress(id, addressDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAll(){
        addressService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
