package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopAffiliate;
import com.thalasoft.learnintouch.data.jpa.domain.ShopDiscount;

public interface ShopDiscountRepository extends GenericRepository<ShopDiscount, Long> {

    @Query("SELECT sd FROM ShopDiscount sd WHERE sd.shopAffiliate = :shopAffiliate ORDER BY sd.discountRate DESC")
    public List<ShopDiscount> findByAffiliate(@Param("shopAffiliate") ShopAffiliate shopAffiliate);
    
    @Query("SELECT sd FROM ShopDiscount sd WHERE sd.discountCode = :discountCode")
    public ShopDiscount findByDiscountCode(@Param("discountCode") String discountCode);

}
