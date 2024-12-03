package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // возвращаем страницу регистрации
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Проверяем, если логин уже занят
        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameError", "Этот логин уже занят");
            return "register";  // Возвращаемся на страницу регистрации с ошибкой
        }

        // Хешируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(password);

        // Создаем нового пользователя с зашифрованным паролем
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodedPassword);

        // Сохраняем пользователя в базе данных
        userService.saveUser(user);

        return "redirect:/login";  // Перенаправление на страницу логина
    }
}

