package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeWebController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showFilterForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "home";
    }

}
