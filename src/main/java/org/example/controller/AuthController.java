package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // возвращаем имя шаблона login.html
    }
    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String username,
                                   @RequestParam String password) {
        // Простая проверка (в будущем замените на проверку из базы данных)
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/"; // Успешный вход, перенаправляем на главную страницу
        }
        return "redirect:/login?error"; // Ошибка входа, возвращаем на страницу входа
    }
}