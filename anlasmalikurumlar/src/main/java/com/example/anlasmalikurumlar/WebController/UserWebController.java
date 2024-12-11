package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.dto.CategoryDTO;
import com.example.anlasmalikurumlar.dto.CompaniesDTO;
import com.example.anlasmalikurumlar.restController.CategoryRestController;
import com.example.anlasmalikurumlar.restController.CompaniesRestController;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user/companies")
public class UserWebController {

    @Autowired
    private CompaniesRestController companiesRestController;

    @Autowired
    private CategoryRestController categoryRestController;


    @GetMapping
    public String getAllCompanies(Model model) {
        List<CompaniesDTO> companiesDTOs = companiesRestController.getAllCompanies();
        model.addAttribute("companies", companiesDTOs);


        List<CategoryDTO> categories = categoryRestController.getAllCategories();
        model.addAttribute("categories", categories);

        return "user_companies";
    }


    @PostMapping("/filter")
    public String filterCompanies(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer discountPercentage,
            @RequestParam(required = false) String endDate,
            Model model) {

        LocalDate end = endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null;
        List<CompaniesDTO> companies = companiesRestController.filterCompanies(categoryId, discountPercentage, end);

        model.addAttribute("companies", companies);
        model.addAttribute("categories", categoryRestController.getAllCategories());

        return "user_companies";
    }
}
