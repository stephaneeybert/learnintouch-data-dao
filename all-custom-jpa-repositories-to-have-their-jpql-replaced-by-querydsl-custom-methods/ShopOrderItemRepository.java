package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopItem;
import com.thalasoft.learnintouch.data.jpa.domain.ShopOrder;
import com.thalasoft.learnintouch.data.jpa.domain.ShopOrderItem;

public interface ShopOrderItemRepository extends GenericRepository<ShopOrderItem, Long> {

    @Query("SELECT soi FROM ShopOrderItem soi WHERE LOWER(soi.reference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(soi.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(soi.shortDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY soi.name")
    public Page<ShopOrderItem> search(@Param("searchTerm") String searchTerm, Pageable page);
    
    @Query("SELECT soi FROM ShopOrderItem soi WHERE soi.shopOrder = :shopOrder ORDER BY soi.name")
    public Page<ShopOrderItem> findByOrder(@Param("shopOrder") ShopOrder shopOrder, Pageable page);
    
    @Query("SELECT soi FROM ShopOrderItem soi WHERE soi.shopItem = :shopItem ORDER BY soi.name")
    public Page<ShopOrderItem> findByItem(@Param("shopItem") ShopItem shopItem, Pageable page);
    
    @Query("SELECT soi FROM ShopOrderItem soi WHERE soi.shopOrder = :shopOrder AND soi.shopItem = :shopItem")
    public ShopOrderItem findByOrderAndItem(@Param("shopOrder") ShopOrder shopOrder, @Param("shopItem") ShopItem shopItem);
    
}
