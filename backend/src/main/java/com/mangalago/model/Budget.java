package com.mangalago.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    @JsonIgnore
    private Trip trip;
    
    @Column(nullable = false)
    private Double totalBudget;
    
    private Double flightsBudget;
    
    private Double hotelsBudget;
    
    private Double activitiesBudget;
    
    private Double foodBudget;
    
    private Double transportationBudget;
    
    private Double shoppingBudget;
    
    private Double miscellaneousBudget;
    
    @Column(nullable = false)
    private Double spentAmount = 0.0;
    
    @Column(length = 500)
    private String notes;
}
