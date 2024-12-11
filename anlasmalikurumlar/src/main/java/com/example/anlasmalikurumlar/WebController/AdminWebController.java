package com.example.anlasmalikurumlar.WebController;


import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RolesAllowed("ADMIN")
@RequestMapping("/admin")
@Controller
public class AdminWebController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/companies")
    public String companies() {

        return "companies";
    }

    @GetMapping("/categories")
    public String categories() {

        return "categories";
    }

    @GetMapping("/communications")
    public String communications() {

        return "communications";
    }

    @GetMapping("/documents")
    public String documents() {

        return "documents";
    }
}
