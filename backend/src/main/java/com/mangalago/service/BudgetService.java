package com.mangalago.service;

import com.mangalago.model.Budget;
import com.mangalago.model.Trip;
import com.mangalago.repository.BudgetRepository;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final TripRepository tripRepository;
    
    public Budget getBudgetByTripId(Long tripId) {
        return budgetRepository.findByTripId(tripId)
            .orElseThrow(() -> new RuntimeException("Budget not found for trip id: " + tripId));
    }
    
    @Transactional
    public Budget createBudget(Long tripId, Budget budget) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
        
        budget.setTrip(trip);
        return budgetRepository.save(budget);
    }
    
    @Transactional
    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = budgetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));
        
        budget.setTotalBudget(budgetDetails.getTotalBudget());
        budget.setFlightsBudget(budgetDetails.getFlightsBudget());
        budget.setHotelsBudget(budgetDetails.getHotelsBudget());
        budget.setActivitiesBudget(budgetDetails.getActivitiesBudget());
        budget.setFoodBudget(budgetDetails.getFoodBudget());
        budget.setTransportationBudget(budgetDetails.getTransportationBudget());
        budget.setShoppingBudget(budgetDetails.getShoppingBudget());
        budget.setMiscellaneousBudget(budgetDetails.getMiscellaneousBudget());
        budget.setSpentAmount(budgetDetails.getSpentAmount());
        budget.setNotes(budgetDetails.getNotes());
        
        return budgetRepository.save(budget);
    }
}
