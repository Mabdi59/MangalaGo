package com.mangalago.controller;

import com.mangalago.model.Budget;
import com.mangalago.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class BudgetController {
    
    private final BudgetService budgetService;
    
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<Budget> getBudgetByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(budgetService.getBudgetByTripId(tripId));
    }
    
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Budget> createBudget(@PathVariable Long tripId, @RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(budgetService.createBudget(tripId, budget));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.updateBudget(id, budget));
    }
}
