package com.mangalago.service;

import com.mangalago.model.Flight;
import com.mangalago.model.Trip;
import com.mangalago.repository.FlightRepository;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    
    private final FlightRepository flightRepository;
    private final TripRepository tripRepository;
    
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }
    
    public List<Flight> getFlightsByTripId(Long tripId) {
        return flightRepository.findByTripId(tripId);
    }
    
    @Transactional
    public Flight createFlight(Long tripId, Flight flight) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
        
        flight.setTrip(trip);
        return flightRepository.save(flight);
    }
    
    @Transactional
    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = getFlightById(id);
        
        flight.setAirline(flightDetails.getAirline());
        flight.setFlightNumber(flightDetails.getFlightNumber());
        flight.setDepartureAirport(flightDetails.getDepartureAirport());
        flight.setArrivalAirport(flightDetails.getArrivalAirport());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setConfirmationNumber(flightDetails.getConfirmationNumber());
        flight.setPrice(flightDetails.getPrice());
        flight.setSeatNumber(flightDetails.getSeatNumber());
        flight.setNotes(flightDetails.getNotes());
        
        return flightRepository.save(flight);
    }
    
    @Transactional
    public void deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }
}
