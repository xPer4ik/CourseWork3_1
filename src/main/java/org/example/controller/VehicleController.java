package org.example.controller;

import org.example.model.Vehicle;
import org.example.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/vehicles")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @GetMapping("/vehiclesadd-vehicle")
    public String showAddVehicleForm(Model model, Principal principal) {
        System.out.println("Username: " + principal.getName());
        if (principal != null) {
            System.out.println("Username: " + principal.getName()); // Проверка значения имени пользователя
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("vehicle", new Vehicle());
        return "add-vehicle";
    }



    @PostMapping("/vehicles/add-vehicle2")
    public String addVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/booking";
    }

    @DeleteMapping("/vehicles/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
