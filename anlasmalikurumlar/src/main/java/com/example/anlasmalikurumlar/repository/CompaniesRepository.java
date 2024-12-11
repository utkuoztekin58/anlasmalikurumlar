package com.example.anlasmalikurumlar.repository;

import com.example.anlasmalikurumlar.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {

    @Query(value = "SELECT * FROM Companies c,Categories c1 WHERE c.category_id = c1.id " +
            "(:categoryId IS NULL OR c.category_id = :categoryId) AND " +
            "(:discountPercentage IS NULL OR c.company_discount_percantage = :discountPercentage) AND " +
            "(:startDate IS NULL OR c.start_date >= :startDate) AND " +
            "(:endDate IS NULL OR c.end_date <= :endDate)", nativeQuery = true)
    List<Companies> findByFilters(@Param("categoryId") Long categoryId,
                                  @Param("discountPercentage") Integer discountPercentage,
                                  @Param("startDate") Date startDate,
                                  @Param("endDate") Date endDate);


    List<Companies> findAllByCategoryIdAndDiscountPercentageAndStartDateBeforeAndEndDateAfter(Long categoryId, Integer discountPercentage, LocalDate startDate, LocalDate endDate);

    List<Companies> findAllByCategoryIdAndStartDateBeforeAndEndDateAfter(Long categoryId, LocalDate startDate, LocalDate endDate);
    boolean existsByCategoryId(Long categoryId);

}
