package com.example.anlasmalikurumlar.WebController;

import com.example.anlasmalikurumlar.dto.CategoryDTO;
import com.example.anlasmalikurumlar.dto.CompaniesDTO;
import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.restController.CategoryRestController;
import com.example.anlasmalikurumlar.restController.CompaniesRestController;
import com.example.anlasmalikurumlar.service.CompaniesService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class CompaniesWebController {

    private final CompaniesRestController companiesRestController;
    private final CategoryRestController categoryRestController;
    private final CompaniesService companiesService;

    @Autowired
    public CompaniesWebController(
            CompaniesRestController companiesRestController,
            CategoryRestController categoryRestController,
            CompaniesService companiesService) {
        this.companiesRestController = companiesRestController;
        this.categoryRestController = categoryRestController;
        this.companiesService = companiesService;
    }

    @GetMapping("/companies")
    public String getAllCompanies(Model model) {
        List<CompaniesDTO> companiesDTOs = companiesRestController.getAllCompanies();
        model.addAttribute("companies", companiesDTOs);
        List<CategoryDTO> categories = categoryRestController.getAllCategories();
        model.addAttribute("categories", categories);
        return "companies";
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
        return "companies";
    }

    @GetMapping("/companies/new")
    public String showNewCompanyForm(Model model) {
        CompaniesDTO companiesDTO = new CompaniesDTO();
        List<CategoryDTO> categories = categoryRestController.getAllCategories();
        companiesDTO.setStartDate(LocalDate.now());
        companiesDTO.setEndDate(LocalDate.now());
        model.addAttribute("companiesDTO", companiesDTO);
        model.addAttribute("categories", categories);
        return "new_company";
    }

    @PostMapping("/companies/save")
    public String saveCompany(@ModelAttribute("companiesDTO") CompaniesDTO companiesDTO, RedirectAttributes redirectAttributes) {
        // Validate email format
        if (!isValidEmail(companiesDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Geçersiz email adresi formatı.");
            return "redirect:/companies/new";
        }

        // Save company
        Companies companies = mapToEntity(companiesDTO);
        companiesService.saveCompanies(companies);

        redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla kaydedildi.");
        return "redirect:/companies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        CompaniesDTO companiesDTO = companiesRestController.getCompanies(id);
        List<CategoryDTO> categories = categoryRestController.getAllCategories();
        model.addAttribute("companiesDTO", companiesDTO);
        model.addAttribute("categories", categories);
        return "edit_company";
    }

    @PostMapping("/companies/update")
    public String updateCompanies(@ModelAttribute("companiesDTO") CompaniesDTO companiesDTO, RedirectAttributes redirectAttributes) {
        // Validate email format
        if (!isValidEmail(companiesDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Geçersiz email adresi formatı.");
            return "redirect:/companies/edit/" + companiesDTO.getId();
        }


        Companies companies = mapToEntity(companiesDTO);
        companiesRestController.updateCompanies(companiesDTO.getId(), companiesDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla güncellendi.");
        return "redirect:/companies";
    }

    @PostMapping("/companies/delete/{id}")
    public String deleteCompanies(@PathVariable long id, RedirectAttributes redirectAttributes) {
        System.out.println(id);
        try {
            companiesRestController.deleteCompanies(id);
            redirectAttributes.addFlashAttribute("successMessage", "Şirket başarıyla silindi.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Şirket silinirken bir hata oluştu.");
        }
        return "redirect:/companies";
    }

    private Companies mapToEntity(CompaniesDTO dto) {
        Companies companies = new Companies();
        companies.setId(dto.getId());
        companies.setName(dto.getName());
        companies.setEmail(dto.getEmail());
        companies.setDiscountPercentage(dto.getDiscountPercentage());
        companies.setStartDate(dto.getStartDate());
        companies.setEndDate(dto.getEndDate());
        companies.setCategoryId(dto.getCategoryId());
        return companies;
    }

    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}
