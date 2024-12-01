package org.example.controller;

import org.example.model.Vehicle;
import org.example.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {
    private final VehicleService vehicleService;

    public BookingController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/booking")
    public String bookingPage(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "booking"; // Вернет шаблон `booking.html`
    }
}
