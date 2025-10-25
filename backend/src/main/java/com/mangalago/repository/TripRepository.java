package com.mangalago.repository;

import com.mangalago.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByStatus(String status);
    List<Trip> findByDestinationContainingIgnoreCase(String destination);
    List<Trip> findByStartDateBetween(LocalDate start, LocalDate end);
}
