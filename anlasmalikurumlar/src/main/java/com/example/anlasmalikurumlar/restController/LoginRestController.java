package com.example.anlasmalikurumlar.restController;


import com.example.anlasmalikurumlar.config.SecurityConfig;
import com.example.anlasmalikurumlar.model.Role;
import com.example.anlasmalikurumlar.model.User;
import com.example.anlasmalikurumlar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    //@PostMapping("/login")
    public String login1(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);

        User user = userService.findByUsername(username);


        List<String> roleNames = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String name = role.getName();
            roleNames.add(name);
        }

        if (roleNames.contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        if (roleNames.contains("ROLE_USER")) {
            return "redirect:/user/companies";
        }

        return "redirect:/login";
    }

}
