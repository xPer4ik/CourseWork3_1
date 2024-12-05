package org.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Secured("ROLE_ADMIN")
    @GetMapping("/add-vehicle")
    public String addVehiclePage() {
        return "add-vehicle";
    }
}

