package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopCategory;
import com.thalasoft.learnintouch.data.jpa.domain.ShopItem;
import com.thalasoft.learnintouch.data.jpa.domain.SmsList;

public interface ShopItemRepository extends GenericRepository<ShopItem, Long> {

    @Query("SELECT si FROM ShopItem si WHERE LOWER(si.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(si.shortDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(si.longDescription) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(si.reference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY si.name")
    public Page<SmsList> search(@Param("searchTerm") String searchTerm, Pageable page);
    
    @Query("SELECT si FROM ShopItem si WHERE si.shopCategory = :shopCategory OR (si.shopCategory IS NULL AND :shopCategory < '1') ORDER BY si.listOrder")
    public ShopItem findByCategory(@Param("shopCategory") ShopCategory shopCategory);

    @Query("SELECT si FROM ShopItem si WHERE si.shopCategory = :shopCategory OR (si.shopCategory IS NULL AND :shopCategory < '1') ORDER BY si.id")
    public ShopItem findByCategoryOrderById(@Param("shopCategory") ShopCategory shopCategory);

    @Query("SELECT si FROM ShopItem si WHERE (si.shopCategory = :shopCategory OR (si.shopCategory IS NULL AND :shopCategory < '1')) AND si.listOrder > :listOrder ORDER BY si.listOrder ASC LIMIT 1")
    public ShopItem findByNextListOrder(@Param("shopCategory") ShopCategory shopCategory, @Param("listOrder") int listOrder);

    @Query("SELECT si FROM ShopItem si WHERE (si.shopCategory = :shopCategory OR (si.shopCategory IS NULL AND :shopCategory < '1')) AND si.listOrder < :listOrder ORDER BY si.listOrder DESC LIMIT 1")
    public ShopItem findByPreviousListOrder(@Param("shopCategory") ShopCategory shopCategory, @Param("listOrder") int listOrder);

    @Query("SELECT si FROM ShopItem si WHERE (si.shopCategory = :shopCategory OR (si.shopCategory IS NULL AND :shopCategory < '1')) AND si.listOrder = :listOrder ORDER BY si.listOrder DESC")
    public List<ShopItem> findByListOrder(@Param("shopCategory") ShopCategory shopCategory, @Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT si1.id) as count FROM ShopItem si1, ShopItem si2 WHERE si1.id != si2.id AND si1.shopCategory.id = si2.shopCategory.id AND si1.listOrder = si2.listOrder AND si1.shopCategory = :shopCategory")
    public Long countDuplicateListOrders(@Param("shopCategory") ShopCategory shopCategory);
    
}
