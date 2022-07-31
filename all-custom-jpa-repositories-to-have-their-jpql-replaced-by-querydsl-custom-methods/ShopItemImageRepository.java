package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopItem;
import com.thalasoft.learnintouch.data.jpa.domain.ShopItemImage;

public interface ShopItemImageRepository extends GenericRepository<ShopItemImage, Long> {

    @Query("SELECT sii FROM ShopItemImage sii WHERE sii.shopItem = :shopItem AND sii.listOrder = :listOrder ORDER BY sii.listOrder DESC")
    public List<ShopItemImage> findByListOrder(@Param("shopItem") ShopItem shopItem, @Param("listOrder") int listOrder);

    @Query("SELECT sii FROM ShopItemImage sii WHERE sii.shopItem = :shopItem AND sii.listOrder > :listOrder ORDER BY sii.listOrder ASC LIMIT 1")
    public ShopItemImage findByNextListOrder(@Param("shopItem") ShopItem shopItem, @Param("listOrder") int listOrder);
    
    @Query("SELECT sii FROM ShopItemImage sii WHERE sii.shopItem = :shopItem AND sii.listOrder < :listOrder ORDER BY sii.listOrder DESC LIMIT 1")
    public ShopItemImage findByPreviousListOrder(@Param("shopItem") ShopItem shopItem, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT sii1.id) as count FROM ShopItemImage sii1, ShopItemImage sii2 WHERE sii1.id != sii2.id AND sii1.shopItem.id = sii2.shopItem.id AND sii1.listOrder = sii2.listOrder AND sii1.shopItem = :shopItem")
    public Long countDuplicateListOrders(@Param("shopItem") ShopItem shopItem);
    
    @Query("SELECT sii FROM ShopItemImage sii WHERE sii.shopItem = :shopItem ORDER BY sii.listOrder")
    public ShopItemImage findByShopItem(@Param("shopItem") ShopItem shopItem);

    @Query("SELECT sii FROM ShopItemImage sii WHERE sii.shopItem = :shopItem ORDER BY sii.id")
    public ShopItemImage findByShopItemOrderById(@Param("shopItem") ShopItem shopItem);

    public List<ShopItemImage> findByImage(String image);

}

