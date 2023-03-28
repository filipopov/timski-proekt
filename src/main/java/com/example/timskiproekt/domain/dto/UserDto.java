package com.example.timskiproekt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    String email;
    String firstName;

    String lastName;

    String phoneNumber;
}
