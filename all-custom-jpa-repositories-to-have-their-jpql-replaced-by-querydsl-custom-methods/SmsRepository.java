package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Admin;
import com.thalasoft.learnintouch.data.jpa.domain.Sms;
import com.thalasoft.learnintouch.data.jpa.domain.SmsCategory;

public interface SmsRepository extends GenericRepository<Sms, Long> {

    @Query("SELECT s FROM Sms s ORDER BY s.body")
    public Page<Sms> findThemAll(Pageable page);

    @Query("SELECT s FROM Sms s WHERE s.admin = :admin OR (s.admin IS NULL AND :admin < '1') ORDER BY s.body")
    public Page<Sms> findByAdmin(@Param("admin") Admin admin, Pageable page);

    @Query("SELECT s FROM Sms s WHERE s.smsCategory = :smsCategory OR (s.smsCategory IS NULL AND :smsCategory < '1') ORDER BY s.body")
    public Page<Sms> findByCategory(@Param("smsCategory") SmsCategory smsCategory, Pageable page);
    
    @Query("SELECT s FROM Sms s WHERE (s.admin = :admin OR (s.admin IS NULL AND :admin < '1')) AND (s.smsCategory = :smsCategory OR (s.smsCategory IS NULL AND :smsCategory < '1')) ORDER BY s.body")
    public Page<Sms> findByAdminAndCategory(@Param("admin") Admin admin, @Param("smsCategory") SmsCategory smsCategory, Pageable page);
    
    @Query("SELECT s FROM Sms s, Admin a, SmsCategory sc WHERE (s.admin.id = a.id OR s.admin IS NULL) AND (s.smsCategory.id = sc.id OR s.smsCategory IS NULL) AND LOWER(s.body) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.login) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(sc.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY s.smsCategory")
    public Page<Sms> search(@Param("searchTerm") String searchTerm, Pageable page);

}
