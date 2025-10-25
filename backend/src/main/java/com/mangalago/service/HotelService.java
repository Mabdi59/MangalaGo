package com.mangalago.service;

import com.mangalago.model.Hotel;
import com.mangalago.model.Trip;
import com.mangalago.repository.HotelRepository;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    
    private final HotelRepository hotelRepository;
    private final TripRepository tripRepository;
    
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }
    
    public List<Hotel> getHotelsByTripId(Long tripId) {
        return hotelRepository.findByTripId(tripId);
    }
    
    @Transactional
    public Hotel createHotel(Long tripId, Hotel hotel) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
        
        hotel.setTrip(trip);
        return hotelRepository.save(hotel);
    }
    
    @Transactional
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotelById(id);
        
        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setCity(hotelDetails.getCity());
        hotel.setCountry(hotelDetails.getCountry());
        hotel.setCheckInDate(hotelDetails.getCheckInDate());
        hotel.setCheckOutDate(hotelDetails.getCheckOutDate());
        hotel.setConfirmationNumber(hotelDetails.getConfirmationNumber());
        hotel.setPricePerNight(hotelDetails.getPricePerNight());
        hotel.setNumberOfNights(hotelDetails.getNumberOfNights());
        hotel.setRoomType(hotelDetails.getRoomType());
        hotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        hotel.setNotes(hotelDetails.getNotes());
        
        return hotelRepository.save(hotel);
    }
    
    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}
