package com.expensetracker.Controller;

import com.expensetracker.Model.Expense;
import com.expensetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/create")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(savedExpense);

    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Optional<Expense> existingExpense = expenseService.getExpenseById(id);
        if (existingExpense.isPresent()) {
            expense.setId(id);
            Expense updatedExpense = expenseService.updateExpense(expense);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public List<Expense> searchExpenses(
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {
        return expenseService.searchExpenses(description, category, startDate, endDate, minAmount, maxAmount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
