package com.example.anlasmalikurumlar.restController;


import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AdminRestController {


    @GetMapping("/admin")
    public String adminPage(){

        return "admin";
    }
}
