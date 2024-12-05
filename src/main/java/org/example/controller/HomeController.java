package org.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            String username = authentication.getName();
            String role = ((UserDetails) authentication.getPrincipal()).getAuthorities().toString();
            System.out.println("Аутентифицирован пользователь: " + username + role); // Логирование
            model.addAttribute("username", username);
            model.addAttribute("role", role);
        } else {
            System.out.println("Пользователь не аутентифицирован.");
        }
        return "index";
    }
}
