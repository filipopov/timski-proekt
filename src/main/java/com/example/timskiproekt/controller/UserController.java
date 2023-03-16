package com.example.timskiproekt.controller;

import com.example.timskiproekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
}
