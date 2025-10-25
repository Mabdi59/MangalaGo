package com.mangalago.controller;

import com.mangalago.model.Flight;
import com.mangalago.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class FlightController {
    
    private final FlightService flightService;
    
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }
    
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Flight>> getFlightsByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(flightService.getFlightsByTripId(tripId));
    }
    
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Flight> createFlight(@PathVariable Long tripId, @RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(flightService.createFlight(tripId, flight));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
