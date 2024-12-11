package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.dto.CategoryDTO;
import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.service.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller
@RequestMapping("/categories")
public class CategoriesWebController {

    private final CategoryService categoryService;


    @Autowired
    public CategoriesWebController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/new")
    public String showNewCategoryForm(Model model) {

        return "new_category";
    }

    @PostMapping("/save")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryService.save(category);
        return "redirect:/categories/";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/categories/list";
        }
        model.addAttribute("category", category);
        return "edit_category";
    }


    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        categoryService.updateCategory(category);
        return "redirect:/categories/";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (categoryService.isCategoryAssociatedWithCompanies(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Bu kategori bir şirkete bağlı olduğu için silinemiyor. Lütfen şirketleri kontrol ediniz.");
            return "redirect:/categories/";
        }
        categoryService.deleteCategoryById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Kategori başarıyla silindi.");
        return "redirect:/categories/";
    }
}
