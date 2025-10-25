package com.mangalago.controller;

import com.mangalago.model.Activity;
import com.mangalago.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ActivityController {
    
    private final ActivityService activityService;
    
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getActivityById(id));
    }
    
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Activity>> getActivitiesByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(activityService.getActivitiesByTripId(tripId));
    }
    
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Activity> createActivity(@PathVariable Long tripId, @RequestBody Activity activity) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(activityService.createActivity(tripId, activity));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.updateActivity(id, activity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
