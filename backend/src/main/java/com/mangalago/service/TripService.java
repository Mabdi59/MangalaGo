package com.mangalago.service;

import com.mangalago.model.Trip;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    
    private final TripRepository tripRepository;
    
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
    
    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
    }
    
    public List<Trip> getTripsByStatus(String status) {
        return tripRepository.findByStatus(status);
    }
    
    public List<Trip> searchTripsByDestination(String destination) {
        return tripRepository.findByDestinationContainingIgnoreCase(destination);
    }
    
    @Transactional
    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }
    
    @Transactional
    public Trip updateTrip(Long id, Trip tripDetails) {
        Trip trip = getTripById(id);
        
        trip.setName(tripDetails.getName());
        trip.setDescription(tripDetails.getDescription());
        trip.setDestination(tripDetails.getDestination());
        trip.setStartDate(tripDetails.getStartDate());
        trip.setEndDate(tripDetails.getEndDate());
        trip.setStatus(tripDetails.getStatus());
        
        return tripRepository.save(trip);
    }
    
    @Transactional
    public void deleteTrip(Long id) {
        Trip trip = getTripById(id);
        tripRepository.delete(trip);
    }
    
    public List<Trip> getUpcomingTrips() {
        LocalDate today = LocalDate.now();
        LocalDate oneYearLater = today.plusYears(1);
        return tripRepository.findByStartDateBetween(today, oneYearLater);
    }
}
