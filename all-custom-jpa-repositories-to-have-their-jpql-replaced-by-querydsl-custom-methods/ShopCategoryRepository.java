package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopCategory;

public interface ShopCategoryRepository extends GenericRepository<ShopCategory, Long> {

    @Query("SELECT sc FROM ShopCategory sc WHERE sc.parent = :shopCategory OR (sc.parent IS NULL AND :parentCategory < '1') ORDER BY sc.listOrder")
    public ShopCategory findByParentCategory(@Param("parentCategory") ShopCategory parentCategory);

    @Query("SELECT sc FROM ShopCategory sc WHERE sc.parent = :shopCategory OR (sc.parent IS NULL AND :parentCategory < '1') ORDER BY sc.id")
    public ShopCategory findByParentCategoryOrderById(@Param("parentCategory") ShopCategory parentCategory);

    @Query("SELECT sc FROM ShopCategory sc WHERE (sc.parent = :shopCategory OR (sc.parent IS NULL AND :parentCategory < '1')) AND sc.listOrder > :listOrder ORDER BY sc.listOrder ASC LIMIT 1")
    public ShopCategory findByNextListOrder(@Param("parentCategory") ShopCategory parentCategory, @Param("listOrder") int listOrder);

    @Query("SELECT sc FROM ShopCategory sc WHERE (sc.parent = :shopCategory OR (sc.parent IS NULL AND :parentCategory < '1')) AND sc.listOrder < :listOrder ORDER BY sc.listOrder DESC LIMIT 1")
    public ShopCategory findByPreviousListOrder(@Param("parentCategory") ShopCategory parentCategory, @Param("listOrder") int listOrder);

    @Query("SELECT sc FROM ShopCategory sc WHERE (sc.parent = :shopCategory OR (sc.parent IS NULL AND :parentCategory < '1')) AND sc.listOrder = :listOrder ORDER BY sc.listOrder DESC")
    public List<ShopCategory> findByListOrder(@Param("parentCategory") ShopCategory parentCategory, @Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT sc1.id) as count FROM ShopCategory sc1, ShopCategory sc2 WHERE sc1.id != sc2.id AND sc1.parent.id = sc2.parent.id AND sc1.listOrder = sc2.listOrder AND sc1.parent = :parentCategory")
    public Long countDuplicateListOrders(@Param("parentCategory") ShopCategory parentCategory);
    
}
