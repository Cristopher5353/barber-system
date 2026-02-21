package com.cja.inventory.repositories;

import com.cja.inventory.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByBusinessIdAndStockIsNullAndActiveTrue(Long businessId);
    List<Item> findByBusinessIdAndStockIsNotNullAndActiveTrue(Long businessId);
}
