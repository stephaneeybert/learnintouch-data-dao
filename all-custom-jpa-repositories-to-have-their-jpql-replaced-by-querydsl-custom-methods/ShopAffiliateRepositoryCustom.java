package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.ShopAffiliate;

public interface ShopAffiliateRepositoryCustom {

    public Page<ShopAffiliate> search(String searchTerm, Pageable page);

}
