package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopAffiliate;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface ShopAffiliateRepository extends GenericRepository<ShopAffiliate, Long> {

    @Query("SELECT sa FROM ShopAffiliate sa, UserAccount ua WHERE sa.userAccount.id = ua.id ORDER BY ua.lastname, ua.firstname")
    public List<ShopAffiliate> findThemAll();
    
    @Query("SELECT sa FROM ShopAffiliate sa WHERE sa.userAccount = :userAccount")
    public ShopAffiliate findByUserAccount(@Param("userAccount") UserAccount userAccount);

}
