package com.mangalago.controller;

import com.mangalago.model.PackingItem;
import com.mangalago.model.PackingList;
import com.mangalago.service.PackingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/packing")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class PackingListController {
    
    private final PackingListService packingListService;
    
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<PackingList> getPackingListByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(packingListService.getPackingListByTripId(tripId));
    }
    
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<PackingList> createPackingList(@PathVariable Long tripId) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(packingListService.createPackingList(tripId));
    }
    
    @GetMapping("/{packingListId}/items")
    public ResponseEntity<List<PackingItem>> getPackingItems(@PathVariable Long packingListId) {
        return ResponseEntity.ok(packingListService.getPackingItems(packingListId));
    }
    
    @PostMapping("/{packingListId}/items")
    public ResponseEntity<PackingItem> addPackingItem(@PathVariable Long packingListId, @RequestBody PackingItem item) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(packingListService.addPackingItem(packingListId, item));
    }
    
    @PutMapping("/items/{itemId}")
    public ResponseEntity<PackingItem> updatePackingItem(@PathVariable Long itemId, @RequestBody PackingItem item) {
        return ResponseEntity.ok(packingListService.updatePackingItem(itemId, item));
    }
    
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deletePackingItem(@PathVariable Long itemId) {
        packingListService.deletePackingItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
