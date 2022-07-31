package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.jpa.domain.ShopAffiliate;

public class ShopAffiliateRepositoryImpl implements ShopAffiliateRepositoryCustom {

    @Autowired
    private ShopAffiliateRepository shopAffiliateRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ShopAffiliate> search(String searchTerm, Pageable page) {
        String sqlStatement = "SELECT sa FROM ShopAffiliate sa, UserAccount ua WHERE sauserAccount.id = ua.id AND (LOWER(ua.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.homePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.workPhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.fax) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))";
         if (searchTerm.contains(" ")) {
             String[] bits = searchTerm.split(" ");
             String firstname = bits[0];
             String lastname = bits[1];
             sqlStatement += " OR (LOWER(ua.firstname) LIKE LOWER(CONCAT('%', " + firstname + ", '%')) AND LOWER(ua.lastname) LIKE LOWER(CONCAT('%', " + lastname + ", '%')))";
         }
         sqlStatement += " ORDER BY ua.lastname, ua.firstname";
         
         TypedQuery<ShopAffiliate> query = shopAffiliateRepository.getEntityManager().createQuery(sqlStatement, ShopAffiliate.class);

         query.setParameter("searchTerm", searchTerm);
         
         List<ShopAffiliate> resultList = query.getResultList();
         long total = resultList.size();
         query.setFirstResult(page.getOffset());
         query.setMaxResults(page.getPageSize());
         resultList = query.getResultList();
         Page<ShopAffiliate> shopAffiliates = new PageImpl<ShopAffiliate>(resultList, page, total);
         
         return shopAffiliates;
    }

}
