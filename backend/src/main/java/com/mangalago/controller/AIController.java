package com.mangalago.controller;

import com.mangalago.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class AIController {
    
    private final AIService aiService;
    
    @GetMapping("/trip-ideas")
    public ResponseEntity<List<String>> getTripIdeas(@RequestParam String destination) {
        return ResponseEntity.ok(aiService.generateTripIdeas(destination));
    }
    
    @GetMapping("/packing-suggestions")
    public ResponseEntity<List<String>> getPackingSuggestions(
            @RequestParam String destination,
            @RequestParam int durationDays,
            @RequestParam(required = false) String season) {
        return ResponseEntity.ok(aiService.generatePackingSuggestions(destination, durationDays, season));
    }
    
    @PostMapping("/optimize-itinerary")
    public ResponseEntity<List<String>> optimizeItinerary(@RequestBody List<String> activities) {
        return ResponseEntity.ok(aiService.optimizeItinerary(activities));
    }
    
    @GetMapping("/budget-allocation")
    public ResponseEntity<String> getBudgetAllocation(
            @RequestParam double totalBudget,
            @RequestParam(defaultValue = "moderate") String tripType) {
        return ResponseEntity.ok(aiService.suggestBudgetAllocation(totalBudget, tripType));
    }
}
