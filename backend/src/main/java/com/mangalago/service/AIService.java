package com.mangalago.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class AIService {
    
    private final Random random = new Random();
    
    /**
     * Generate AI-powered trip ideas based on destination
     */
    public List<String> generateTripIdeas(String destination) {
        List<String> ideas = new ArrayList<>();
        
        // Basic AI simulation - in production, this would call an actual AI API
        ideas.add("Visit the top historical landmarks in " + destination);
        ideas.add("Explore local cuisine and food markets");
        ideas.add("Experience adventure activities and outdoor sports");
        ideas.add("Discover cultural festivals and events");
        ideas.add("Relax at beaches and natural attractions");
        ideas.add("Shop at traditional markets and boutiques");
        ideas.add("Take guided city tours and walking trails");
        
        return ideas;
    }
    
    /**
     * Generate smart packing suggestions based on trip details
     */
    public List<String> generatePackingSuggestions(String destination, int durationDays, String season) {
        List<String> suggestions = new ArrayList<>();
        
        // Essential items
        suggestions.addAll(Arrays.asList(
            "Passport and travel documents",
            "Travel insurance documents",
            "Credit cards and local currency",
            "Phone charger and power bank",
            "Medications and first-aid kit"
        ));
        
        // Clothing based on duration
        int clothingSets = Math.min(durationDays, 7);
        suggestions.add(clothingSets + " sets of clothing");
        suggestions.add("Underwear and socks for " + durationDays + " days");
        
        // Season-specific items
        if (season != null) {
            switch (season.toLowerCase()) {
                case "summer":
                    suggestions.addAll(Arrays.asList(
                        "Sunscreen and sunglasses",
                        "Light clothing and swimwear",
                        "Hat for sun protection"
                    ));
                    break;
                case "winter":
                    suggestions.addAll(Arrays.asList(
                        "Warm jacket and layers",
                        "Gloves and winter hat",
                        "Thermal underwear"
                    ));
                    break;
                case "spring":
                case "fall":
                    suggestions.addAll(Arrays.asList(
                        "Light jacket or cardigan",
                        "Umbrella or raincoat",
                        "Comfortable walking shoes"
                    ));
                    break;
            }
        }
        
        // Destination-specific
        if (destination.toLowerCase().contains("beach")) {
            suggestions.addAll(Arrays.asList("Beach towel", "Flip flops", "Snorkel gear"));
        }
        
        if (destination.toLowerCase().contains("mountain") || destination.toLowerCase().contains("hiking")) {
            suggestions.addAll(Arrays.asList("Hiking boots", "Backpack", "Water bottle"));
        }
        
        return suggestions;
    }
    
    /**
     * Optimize itinerary by suggesting best order of activities
     */
    public List<String> optimizeItinerary(List<String> activities) {
        List<String> suggestions = new ArrayList<>();
        
        suggestions.add("Group nearby attractions together to minimize travel time");
        suggestions.add("Schedule outdoor activities during good weather hours");
        suggestions.add("Plan meals around activity locations");
        suggestions.add("Include rest breaks between intensive activities");
        suggestions.add("Book popular attractions in advance to avoid queues");
        suggestions.add("Consider local traffic patterns when planning travel times");
        
        return suggestions;
    }
    
    /**
     * Suggest budget allocation based on trip type
     */
    public String suggestBudgetAllocation(double totalBudget, String tripType) {
        StringBuilder suggestion = new StringBuilder();
        suggestion.append("Recommended budget allocation for ").append(tripType).append(" trip:\n\n");
        
        if (tripType.equalsIgnoreCase("luxury")) {
            suggestion.append("Accommodation: 40% ($").append(String.format("%.2f", totalBudget * 0.40)).append(")\n");
            suggestion.append("Dining: 25% ($").append(String.format("%.2f", totalBudget * 0.25)).append(")\n");
            suggestion.append("Activities: 20% ($").append(String.format("%.2f", totalBudget * 0.20)).append(")\n");
            suggestion.append("Shopping: 10% ($").append(String.format("%.2f", totalBudget * 0.10)).append(")\n");
            suggestion.append("Miscellaneous: 5% ($").append(String.format("%.2f", totalBudget * 0.05)).append(")\n");
        } else if (tripType.equalsIgnoreCase("budget")) {
            suggestion.append("Accommodation: 30% ($").append(String.format("%.2f", totalBudget * 0.30)).append(")\n");
            suggestion.append("Dining: 20% ($").append(String.format("%.2f", totalBudget * 0.20)).append(")\n");
            suggestion.append("Activities: 25% ($").append(String.format("%.2f", totalBudget * 0.25)).append(")\n");
            suggestion.append("Transportation: 15% ($").append(String.format("%.2f", totalBudget * 0.15)).append(")\n");
            suggestion.append("Miscellaneous: 10% ($").append(String.format("%.2f", totalBudget * 0.10)).append(")\n");
        } else { // moderate/balanced
            suggestion.append("Accommodation: 35% ($").append(String.format("%.2f", totalBudget * 0.35)).append(")\n");
            suggestion.append("Dining: 20% ($").append(String.format("%.2f", totalBudget * 0.20)).append(")\n");
            suggestion.append("Activities: 25% ($").append(String.format("%.2f", totalBudget * 0.25)).append(")\n");
            suggestion.append("Transportation: 10% ($").append(String.format("%.2f", totalBudget * 0.10)).append(")\n");
            suggestion.append("Miscellaneous: 10% ($").append(String.format("%.2f", totalBudget * 0.10)).append(")\n");
        }
        
        return suggestion.toString();
    }
}
