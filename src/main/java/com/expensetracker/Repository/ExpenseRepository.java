package com.expensetracker.Repository;

import com.expensetracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE " +
            "(:description IS NULL OR e.description LIKE %:description%) AND " +
            "(:category IS NULL OR e.category.id = :category) AND " +
            "(:startDate IS NULL OR e.date >= :startDate) AND " +
            "(:endDate IS NULL OR e.date <= :endDate) AND " +
            "(:minAmount IS NULL OR e.amount >= :minAmount) AND " +
            "(:maxAmount IS NULL OR e.amount <= :maxAmount)")
    List<Expense> searchExpenses(@Param("description") String description,
                                 @Param("category") Long category,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate,
                                 @Param("minAmount") Double minAmount,
                                 @Param("maxAmount") Double maxAmount);
    List<Expense> findByUserId(Long userId);
}

