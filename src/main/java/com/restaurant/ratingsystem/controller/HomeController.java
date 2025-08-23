package com.restaurant.ratingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Restaurant Rating System is running!<br>" +
               "H2 Console: <a href='/h2-console'>/h2-console</a><br>" +
               "JDBC URL: jdbc:h2:mem:testdb<br>" +
               "Username: sa<br>" +
               "Password: (empty)";
    }
}