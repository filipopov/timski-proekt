package com.example.timskiproekt.utils;

import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.Role;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class BaseTestData {

    public static User getUser(Long id,
                               String firstName,
                               String lastName,
                               String username,
                               String email,
                               String phoneNumber,
                               Role role,
                               String address){
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .userRole(role)
                .address(address)
                .cart(null)
                .build();


    }

    public static User generateRandomUser(Boolean withId){
         String firstName = "Stefan";
         String lastName = "Stefanovski";
         Long random = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() + new Random().nextLong();
         String username = "stefan";
         String phoneNumber = "070123456";
         String address = "Address1";

         return getUser(withId ? random : null,
                 firstName,
                 lastName,
                 username,
                 random+"@gmail.com",
                 phoneNumber,
                 Role.USER,
                 address);
    }
}
