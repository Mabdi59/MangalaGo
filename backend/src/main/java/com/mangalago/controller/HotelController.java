package com.mangalago.controller;

import com.mangalago.model.Hotel;
import com.mangalago.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class HotelController {
    
    private final HotelService hotelService;
    
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
    
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Hotel>> getHotelsByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(hotelService.getHotelsByTripId(tripId));
    }
    
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Hotel> createHotel(@PathVariable Long tripId, @RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(hotelService.createHotel(tripId, hotel));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotel));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
