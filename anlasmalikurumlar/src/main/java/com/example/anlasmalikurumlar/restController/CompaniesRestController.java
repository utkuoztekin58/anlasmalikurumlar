package com.example.anlasmalikurumlar.restController;

import com.example.anlasmalikurumlar.dto.CategoryDTO;
import com.example.anlasmalikurumlar.dto.CompaniesDTO;
import com.example.anlasmalikurumlar.dto.CompanyDtoRequest;
import com.example.anlasmalikurumlar.dto.CompanyDtoResponse;
import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.service.CategoryService;
import com.example.anlasmalikurumlar.service.CompaniesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
@Tag(name = "Şirket Bilgileri")
public class CompaniesRestController {

    private final CompaniesService companiesService;
    private final CategoryService categoryService;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CompaniesRestController(CompaniesService companiesService, CategoryService categoryService, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.companiesService = companiesService;
        this.categoryService = categoryService;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @PostMapping("/filter")
    public List<CompaniesDTO> filterCompanies(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer discountPercentage,
            @RequestParam(required = false) LocalDate endDate) {

        String sql = "select * from companies where 0=0";
        Map<String, Object> param = new HashMap<>();
        if (categoryId != null) {
            sql += " and category_id = :categoryId";
            param.put("categoryId", categoryId);
        }
        if (discountPercentage != null) {
            sql += " and discountPercentage = :discountPercentage";
            param.put("discountPercentage", discountPercentage);
        }
        if (endDate != null) {
            sql += " and :endDate between start_date  and  end_date";
            param.put("endDate", endDate);
        }

        List<Companies> companies = namedParameterJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(Companies.class));

        return companies.stream().map(f -> companiesService.convertToDTO(f)).toList();

    }

    @GetMapping("/")
    public List<CompaniesDTO> getAllCompanies() {
        List<Companies> companiesList = companiesService.getAllCompanies();
        return companiesList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<CompanyDtoResponse> addCompanies(@RequestBody CompanyDtoRequest dto) {
        Companies companies = new Companies();
        companies.setName(dto.name);
        companies.setEmail(dto.email);
        companies.setDiscountPercentage(dto.discountPercentage);
        companies.setStartDate(dto.startDate);
        companies.setEndDate(dto.endDate);
        Category category = categoryService.getCategoryById(dto.categoryId);
        companies.setCategoryId(dto.categoryId);
        companies = companiesService.saveCompanies(companies);

        CompanyDtoResponse response = new CompanyDtoResponse();
        response.id = companies.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("companiesDTO") CompaniesDTO companiesDTO) {
        Companies companies = mapToEntity(companiesDTO);
        companiesService.saveCompanies(companies);
        return "redirect:/companies";
    }

    @PutMapping("/{id}")
    public CompaniesDTO updateCompanies(@PathVariable int id, @RequestBody CompaniesDTO companiesDTO) {
        Companies companies = mapToEntity(companiesDTO);
        companies.setId(id);
        companies = companiesService.saveCompanies(companies);
        return mapToDTO(companies);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanies(@PathVariable long id) {
        companiesService.deleteCompanies(id);
    }

    @GetMapping("/{id}")
    public CompaniesDTO getCompanies(@PathVariable long id) {
        Companies companies = companiesService.getCompaniesById(id);
        return mapToDTO(companies);
    }

    private CompaniesDTO mapToDTO(Companies companies) {
        CompaniesDTO dto = new CompaniesDTO();
        dto.setId(companies.getId());
        dto.setName(companies.getName());
        dto.setEmail(companies.getEmail());
        dto.setDiscountPercentage(companies.getDiscountPercentage());
        dto.setStartDate(companies.getStartDate());
        dto.setEndDate(companies.getEndDate());

        if (companies.getCategoryId() != null) {
            dto.setCategoryId(companies.getCategoryId());
        }

        return dto;
    }

    private Companies mapToEntity(CompaniesDTO dto) {
        CategoryDTO categoryDTO = new CategoryDTO();
        Companies companies = new Companies();
        companies.setId(dto.getId());
        companies.setName(dto.getName());
        companies.setEmail(dto.getEmail());
        companies.setDiscountPercentage(dto.getDiscountPercentage());
        companies.setStartDate(dto.getStartDate());
        companies.setEndDate(dto.getEndDate());


        Category category = categoryService.getCategoryById(dto.categoryId);
        companies.setCategoryId(category.getId());
        categoryDTO.setName(categoryDTO.name);

        return companies;
    }
}
