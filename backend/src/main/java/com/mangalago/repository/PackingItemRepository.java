package com.mangalago.repository;

import com.mangalago.model.PackingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PackingItemRepository extends JpaRepository<PackingItem, Long> {
    List<PackingItem> findByPackingListId(Long packingListId);
    List<PackingItem> findByPackingListIdAndPacked(Long packingListId, Boolean packed);
}
