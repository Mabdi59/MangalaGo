package com.mangalago.service;

import com.mangalago.model.Activity;
import com.mangalago.model.Trip;
import com.mangalago.repository.ActivityRepository;
import com.mangalago.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    
    private final ActivityRepository activityRepository;
    private final TripRepository tripRepository;
    
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
    
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
    }
    
    public List<Activity> getActivitiesByTripId(Long tripId) {
        return activityRepository.findByTripId(tripId);
    }
    
    @Transactional
    public Activity createActivity(Long tripId, Activity activity) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));
        
        activity.setTrip(trip);
        return activityRepository.save(activity);
    }
    
    @Transactional
    public Activity updateActivity(Long id, Activity activityDetails) {
        Activity activity = getActivityById(id);
        
        activity.setName(activityDetails.getName());
        activity.setDescription(activityDetails.getDescription());
        activity.setScheduledTime(activityDetails.getScheduledTime());
        activity.setLocation(activityDetails.getLocation());
        activity.setPrice(activityDetails.getPrice());
        activity.setCategory(activityDetails.getCategory());
        activity.setBookingReference(activityDetails.getBookingReference());
        activity.setDurationMinutes(activityDetails.getDurationMinutes());
        activity.setNotes(activityDetails.getNotes());
        
        return activityRepository.save(activity);
    }
    
    @Transactional
    public void deleteActivity(Long id) {
        Activity activity = getActivityById(id);
        activityRepository.delete(activity);
    }
}
