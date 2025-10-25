package com.mangalago.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "packing_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackingItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packing_list_id", nullable = false)
    @JsonIgnore
    private PackingList packingList;
    
    @Column(nullable = false)
    private String name;
    
    private String category; // CLOTHING, TOILETRIES, ELECTRONICS, DOCUMENTS, MEDICATION, etc.
    
    private Integer quantity = 1;
    
    @Column(nullable = false)
    private Boolean packed = false;
    
    @Column(length = 300)
    private String notes;
}
