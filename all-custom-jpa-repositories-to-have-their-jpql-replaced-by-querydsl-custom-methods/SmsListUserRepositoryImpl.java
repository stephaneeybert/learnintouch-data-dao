package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.jpa.domain.SmsList;
import com.thalasoft.learnintouch.data.jpa.domain.SmsListUser;

public class SmsListUserRepositoryImpl implements SmsListUserRepositoryCustom {

    @Autowired
    private SmsListUserRepository smsListUserRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SmsListUser> searchBySmsListAndSubscriber(SmsList smsList, String searchTerm, Pageable page) {
        String sqlStatement = "SELECT slu FROM SmsListUser slu, UserAccount ua WHERE slu.smsUser.id = ua.id AND slu.smsList = :smsList AND ua.smsSubscribe = '1' AND (LOWER(ua.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.homePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.workPhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.fax) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))";
         if (searchTerm.contains(" ")) {
             String[] bits = searchTerm.split(" ");
             String firstname = bits[0];
             String lastname = bits[1];
             sqlStatement += " OR (LOWER(ua.firstname) LIKE LOWER(CONCAT('%', " + firstname + ", '%')) AND LOWER(ua.lastname) LIKE LOWER(CONCAT('%', " + lastname + ", '%')))";
         }
         sqlStatement += " ORDER BY ua.lastname, ua.firstname";
         
         TypedQuery<SmsListUser> query = smsListUserRepository.getEntityManager().createQuery(sqlStatement, SmsListUser.class);

         query.setParameter("smsList", smsList);
         query.setParameter("searchTerm", searchTerm);
         
         List<SmsListUser> resultList = query.getResultList();
         long total = resultList.size();
         query.setFirstResult(page.getOffset());
         query.setMaxResults(page.getPageSize());
         resultList = query.getResultList();
         Page<SmsListUser> smsListUsers = new PageImpl<SmsListUser>(resultList, page, total);
         
         return smsListUsers;
    }

}
