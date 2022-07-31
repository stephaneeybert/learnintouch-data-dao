package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.jpa.domain.MailList;
import com.thalasoft.learnintouch.data.jpa.domain.MailListAddress;

public class MailListAddressRepositoryImpl implements MailListAddressRepositoryCustom {

    @Autowired
    private MailListAddressRepository mailListAddressRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<MailListAddress> searchByMailListAndSubscriber(MailList mailList, String searchTerm, Pageable page) {
        String sqlStatement = "SELECT mla FROM MailListAddress mla, MailAddress ma WHERE mla.mailAddress.id = ma.id AND mla.mailList = :mailList AND ma.subscribe = '1' AND (LOWER(ma.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ma.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ma.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ma.textComment) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ma.country) LIKE LOWER(CONCAT('%', :searchTerm, '%')))";
         if (searchTerm.contains(" ")) {
             String[] bits = searchTerm.split(" ");
             String firstname = bits[0];
             String lastname = bits[1];
             sqlStatement += " OR (LOWER(ma.firstname) LIKE LOWER(CONCAT('%', " + firstname + ", '%')) AND LOWER(ma.lastname) LIKE LOWER(CONCAT('%', " + lastname + ", '%')))";
         }
         sqlStatement += " ORDER BY ma.email";
         
         TypedQuery<MailListAddress> query = mailListAddressRepository.getEntityManager().createQuery(sqlStatement, MailListAddress.class);

         query.setParameter("mailList", mailList);
         query.setParameter("searchTerm", searchTerm);
         
         List<MailListAddress> resultList = query.getResultList();
         long total = resultList.size();
         query.setFirstResult(page.getOffset());
         query.setMaxResults(page.getPageSize());
         resultList = query.getResultList();
         Page<MailListAddress> mailListAddresses = new PageImpl<MailListAddress>(resultList, page, total);

         return mailListAddresses;
    }

}
