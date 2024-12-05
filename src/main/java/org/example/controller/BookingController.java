package org.example.controller;

import org.example.model.Booking;
import org.example.model.Vehicle;
import org.example.service.BookingService;
import org.example.service.VehicleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {
    private final VehicleService vehicleService;
    private final BookingService bookingService;

    public BookingController(VehicleService vehicleService, BookingService bookingService) {
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public String bookingPage(Model model, Principal principal) {
        // Передаем список автомобилей в модель
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = ((UserDetails) authentication.getPrincipal()).getAuthorities().toString(); // Получаем роль пользователя
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            model.addAttribute("role", role);
        }



        return "booking"; // Вернет шаблон `booking.html`
    }

    @PostMapping("/book")
    public String bookVehicle(
            @RequestParam("vehicleId") Long vehicleId,
            @RequestParam("bookingDate") String bookingDate,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        // Преобразуем строку в LocalDate
        LocalDate bookingDateParsed = LocalDate.parse(bookingDate);

        try {
            Booking booking = new Booking();
            booking.setVehicleId(vehicleId);
            booking.setUserId(Long.parseLong(principal.getName()));
            booking.setBookingDate(bookingDateParsed);

            bookingService.saveBooking(booking);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/booking"; // Перенаправляем обратно на страницу бронирования с сообщением об ошибке
        }

        return "redirect:/booking"; // Успешное бронирование
    }

}

