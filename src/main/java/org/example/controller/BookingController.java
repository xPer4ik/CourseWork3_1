package org.example.controller;

import org.example.model.Booking;
import org.example.model.Vehicle;
import org.example.service.BookingService;
import org.example.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        // Проверяем, если пользователь аутентифицирован, передаем его имя в модель
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        return "booking"; // Вернет шаблон `booking.html`
    }

    @PostMapping("/book")
    public String bookVehicle(
            @RequestParam("vehicleId") Long vehicleId,
            @RequestParam("bookingDate") String bookingDate,
            Principal principal) {

        // Убедимся, что пользователь аутентифицирован
        if (principal != null) {
            // Создаем новую запись о бронировании
            Booking booking = new Booking();
            booking.setVehicleId(vehicleId);
            booking.setUserId(Long.parseLong(principal.getName())); // Ваша логика для получения ID пользователя
            booking.setBookingDate(LocalDate.parse(bookingDate));

            // Сохраняем бронирование через сервис
            bookingService.saveBooking(booking);
        }

        // Перенаправляем на страницу с доступными автомобилями
        return "redirect:/booking";
    }
}

