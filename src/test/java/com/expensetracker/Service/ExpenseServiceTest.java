package com.expensetracker.Service;

import com.expensetracker.Model.Expense;
import com.expensetracker.Repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExpenseServiceTests {

    @Autowired
    private ExpenseService expenseService;

    @MockBean
    private ExpenseRepository expenseRepository;

    @Test
    public void testAddExpense() {
        // Test adding an expense
        Expense expense = new Expense();
        expense.setDescription("Groceries");
        expense.setAmount(BigDecimal.valueOf(98.654));
        expense.setDate(LocalDate.now());
        when(expenseRepository.save(expense)).thenReturn(expense);
        assertEquals(expense, expenseService.addExpense(expense));
    }

    @Test
    public void testGetAllExpenses() {
        // Test getting all expenses for a user
        Long userId = 1L;
        List<Expense> expenses = Arrays.asList(new Expense(), new Expense());
        when(expenseRepository.findByUserId(userId)).thenReturn(expenses);
        assertEquals(expenses, expenseService.getAllExpenses(userId));
    }

    @Test
    public void testGetExpenseById() {
        // Test getting an expense by ID
        Long expenseId = 1L;
        Expense expense = new Expense();
        expense.setId(expenseId);
        when(expenseRepository.findById(expenseId)).thenReturn(Optional.of(expense));
        Optional<Expense> retrievedExpense = expenseService.getExpenseById(expenseId);
        assertTrue(retrievedExpense.isPresent());
        assertEquals(expenseId, retrievedExpense.get().getId());
    }

    @Test
    public void testUpdateExpense() {
        // Test updating an expense
        Expense expense = new Expense();
        expense.setId(1L);
        expense.setDescription("Updated Groceries");
        expense.setAmount(BigDecimal.valueOf(60.0));
        expense.setDate(LocalDate.now());

        when(expenseRepository.save(expense)).thenReturn(expense);

        Expense updatedExpense = expenseService.updateExpense(expense);
        assertEquals(expense, updatedExpense);
    }

    @Test
    public void testDeleteExpense() {
        // Test deleting an expense
        Long expenseId = 1L;
        expenseService.deleteExpense(expenseId);
        verify(expenseRepository, times(1)).deleteById(expenseId);
    }



}
