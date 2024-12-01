package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Имя шаблона login.html
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        // Логика для аутентификации пользователя
        // В реальном проекте вы будете использовать Spring Security для аутентификации
        return "redirect:/"; // Перенаправление на главную страницу после успешного входа
    }
}
