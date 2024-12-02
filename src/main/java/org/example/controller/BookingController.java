package org.example.controller;

import org.example.model.Vehicle;
import org.example.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class BookingController {
    private final VehicleService vehicleService;

    public BookingController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/booking")
    public String bookingPage(Model model, Principal principal) {
        // Передаем список доступных автомобилей в модель
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);

        // Проверяем, если пользователь аутентифицирован, передаем его имя в модель
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        return "booking"; // Вернет шаблон `booking.html`
    }
}

