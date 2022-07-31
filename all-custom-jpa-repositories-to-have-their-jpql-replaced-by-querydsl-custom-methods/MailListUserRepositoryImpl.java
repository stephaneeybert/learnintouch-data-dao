package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.jpa.domain.MailList;
import com.thalasoft.learnintouch.data.jpa.domain.MailListUser;

public class MailListUserRepositoryImpl implements MailListUserRepositoryCustom {

    @Autowired
    private MailListUserRepository mailListUserRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<MailListUser> searchByMailListAndSubscriber(MailList mailList, String searchTerm, Pageable page) {
        String sqlStatement = "SELECT mlu FROM MailListUser mlu, UserAccount ua WHERE mlu.mailUser.id = ua.id AND mlu.mailList = :mailList AND ua.mailSubscribe = '1' AND (LOWER(ua.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.homePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.workPhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.fax) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')))";
         if (searchTerm.contains(" ")) {
             String[] bits = searchTerm.split(" ");
             String firstname = bits[0];
             String lastname = bits[1];
             sqlStatement += " OR (LOWER(ua.firstname) LIKE LOWER(CONCAT('%', " + firstname + ", '%')) AND LOWER(ua.lastname) LIKE LOWER(CONCAT('%', " + lastname + ", '%')))";
         }
         sqlStatement += " ORDER BY ua.lastname, ua.firstname";
         
         TypedQuery<MailListUser> query = mailListUserRepository.getEntityManager().createQuery(sqlStatement, MailListUser.class);

         query.setParameter("mailList", mailList);
         query.setParameter("searchTerm", searchTerm);
         
         List<MailListUser> resultList = query.getResultList();
         long total = resultList.size();
         query.setFirstResult(page.getOffset());
         query.setMaxResults(page.getPageSize());
         resultList = query.getResultList();
         Page<MailListUser> mailListUsers = new PageImpl<MailListUser>(resultList, page, total);

         return mailListUsers;
    }

}
