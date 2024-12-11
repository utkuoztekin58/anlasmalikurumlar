//package com.example.anlasmalikurumlar.service;
//
//
//import com.example.anlasmalikurumlar.model.Companies;
//import com.example.anlasmalikurumlar.repository.CompaniesRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CompaniesService {
//
//
//    private CompaniesRepository companiesRepository;
//    public CompaniesService(CompaniesRepository companiesRepository) {
//        this.companiesRepository = companiesRepository;
//    }
//                    //sırala
//    public List<Companies> getAllCompanies() {
//        return companiesRepository.findAll();
//    }
//                   //id'ye göre
//    public Companies getCompaniesById(long id) {
//        return companiesRepository.findById(id).orElseThrow();
//    }
//                   //yeni ekle
//    public Companies saveCompanies(Companies companies) {
//        return companiesRepository.save(companies);
//    }
//                      //sil
//    public void deleteCompanies(long id) {
//        companiesRepository.deleteById(id);
//    }
//
//
//
//
//                               //getter ve setter
//
//
//    public CompaniesRepository getCompaniesRepository() {
//        return companiesRepository;
//    }
//    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
//        this.companiesRepository = companiesRepository;
//    }
//}


package com.example.anlasmalikurumlar.service;

import com.example.anlasmalikurumlar.dto.CategoryDTO;
import com.example.anlasmalikurumlar.dto.CompaniesDTO;
import com.example.anlasmalikurumlar.model.Category;
import com.example.anlasmalikurumlar.model.Companies;
import com.example.anlasmalikurumlar.repository.CategoryRepository;
import com.example.anlasmalikurumlar.repository.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniesService {
    @Autowired
    private CompaniesRepository companiesRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public CompaniesService(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    public CompaniesService(CompaniesRepository companiesRepository, CategoryRepository categoryRepository) {
        this.companiesRepository = companiesRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<CompaniesDTO> filterCompanies(Long categoryId, Integer discountPercentage, LocalDate endDate) {

        List<Companies> companies = companiesRepository.findAllByCategoryIdAndDiscountPercentageAndStartDateBeforeAndEndDateAfter(categoryId, discountPercentage, endDate, endDate);
        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CompaniesDTO> findAllByCategoryIdAndStartDateBeforeAndEndDateAfter(Long categoryId, LocalDate endDate) {
        return companiesRepository.findAllByCategoryIdAndStartDateBeforeAndEndDateAfter(categoryId, endDate, endDate)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }


    public  CompaniesDTO convertToDTO(Companies company) {
        CompaniesDTO dto = new CompaniesDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setEmail(company.getEmail());
        dto.setDiscountPercentage(company.getDiscountPercentage());
        dto.setCategoryId(company.getCategoryId());
        dto.setStartDate(company.getStartDate());
        dto.setEndDate(company.getEndDate());
        return dto;
    }

    public   CategoryDTO convertToCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public List<CompaniesDTO> getAllCompaniesDto() {
        return getAllCompanies().stream().map(f -> convertToDTO(f)).toList();
    }

    public List<Companies> getAllCompanies() {
        return companiesRepository.findAll();
    }

    public Companies getCompaniesById(long id) {
        return companiesRepository.findById(id).orElseThrow();
    }

    public Companies saveCompanies(Companies companies) {
        return companiesRepository.save(companies);
    }

    public void deleteCompanies(long id) {
        companiesRepository.deleteById(id);
    }

    public CompaniesRepository getCompaniesRepository() {
        return companiesRepository;
    }

    public void setCompaniesRepository(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }
}
