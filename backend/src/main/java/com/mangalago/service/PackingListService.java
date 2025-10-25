package com.mangalago.service;

import com.mangalago.model.PackingItem;
import com.mangalago.model.PackingList;
import com.mangalago.model.Trip;
import com.mangalago.repository.PackingItemRepository;
import com.mangalago.repository.PackingListRepository;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackingListService {
    
    private final PackingListRepository packingListRepository;
    private final PackingItemRepository packingItemRepository;
    private final TripRepository tripRepository;
    
    public PackingList getPackingListByTripId(Long tripId) {
        return packingListRepository.findByTripId(tripId)
            .orElseThrow(() -> new RuntimeException("Packing list not found for trip id: " + tripId));
    }
    
    @Transactional
    public PackingList createPackingList(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
        
        PackingList packingList = new PackingList();
        packingList.setTrip(trip);
        return packingListRepository.save(packingList);
    }
    
    @Transactional
    public PackingItem addPackingItem(Long packingListId, PackingItem item) {
        PackingList packingList = packingListRepository.findById(packingListId)
            .orElseThrow(() -> new RuntimeException("Packing list not found with id: " + packingListId));
        
        item.setPackingList(packingList);
        return packingItemRepository.save(item);
    }
    
    @Transactional
    public PackingItem updatePackingItem(Long itemId, PackingItem itemDetails) {
        PackingItem item = packingItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Packing item not found with id: " + itemId));
        
        item.setName(itemDetails.getName());
        item.setCategory(itemDetails.getCategory());
        item.setQuantity(itemDetails.getQuantity());
        item.setPacked(itemDetails.getPacked());
        item.setNotes(itemDetails.getNotes());
        
        return packingItemRepository.save(item);
    }
    
    @Transactional
    public void deletePackingItem(Long itemId) {
        PackingItem item = packingItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Packing item not found with id: " + itemId));
        packingItemRepository.delete(item);
    }
    
    public List<PackingItem> getPackingItems(Long packingListId) {
        return packingItemRepository.findByPackingListId(packingListId);
    }
}
