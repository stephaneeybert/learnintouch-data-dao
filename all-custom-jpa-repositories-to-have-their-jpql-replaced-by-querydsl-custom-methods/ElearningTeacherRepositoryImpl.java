package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;

public class ElearningTeacherRepositoryImpl implements ElearningTeacherRepositoryCustom {

    @Autowired
    private ElearningTeacherRepository elearningTeacherRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ElearningTeacher> search(String searchTerm, Pageable page) {
         String sqlStatement = "SELECT et FROM ElearningTeacher et, UserAccount ua WHERE et.userAccount.id = ua.id AND (LOWER(ua.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ua.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR ua.id = :searchTerm";
         if (searchTerm.contains(" ")) {
             String[] bits = searchTerm.split(" ");
             String firstname = bits[0];
             String lastname = bits[1];
             sqlStatement += " OR (LOWER(ua.firstname) LIKE LOWER(CONCAT('%', " + firstname + ", '%')) AND LOWER(ua.lastname) LIKE LOWER(CONCAT('%', " + lastname + ", '%')))";
         }
         sqlStatement += " ORDER BY el.name";
         
         TypedQuery<ElearningTeacher> query = elearningTeacherRepository.getEntityManager().createQuery(sqlStatement, ElearningTeacher.class);

         query.setParameter("searchTerm", searchTerm);
         
         List<ElearningTeacher> resultList = query.getResultList();
         long total = resultList.size();
         query.setFirstResult(page.getOffset());
         query.setMaxResults(page.getPageSize());
         resultList = query.getResultList();
         Page<ElearningTeacher> elearningTeachers = new PageImpl<ElearningTeacher>(resultList, page, total);

         return elearningTeachers;
    }

}
