package com.example.anlasmalikurumlar.restController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRestController {



    @GetMapping("/user")
    public String user (){

        return "user";
    }
}
