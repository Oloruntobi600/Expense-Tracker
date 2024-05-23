package com.expensetracker.Service;

import com.expensetracker.Model.Expense;
import com.expensetracker.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    public List<Expense> searchExpenses(String description, Long category, LocalDate startDate,
                                        LocalDate endDate, Double minAmount, Double maxAmount) {
        return expenseRepository.searchExpenses(description, category, startDate, endDate, minAmount, maxAmount);
    }
}
