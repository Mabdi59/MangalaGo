package com.mangalago.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    @JsonIgnore
    private Trip trip;
    
    @Column(nullable = false)
    private String airline;
    
    @Column(nullable = false)
    private String flightNumber;
    
    @Column(nullable = false)
    private String departureAirport;
    
    @Column(nullable = false)
    private String arrivalAirport;
    
    @Column(nullable = false)
    private LocalDateTime departureTime;
    
    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    
    private String confirmationNumber;
    
    private Double price;
    
    private String seatNumber;
    
    @Column(length = 500)
    private String notes;
}
