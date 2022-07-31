package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Admin;
import com.thalasoft.learnintouch.data.jpa.domain.MailHistory;
import com.thalasoft.learnintouch.data.jpa.domain.MailList;

public interface MailHistoryRepository extends GenericRepository<MailHistory, Long> {

    @Query("DELETE FROM MailHistory mh")
    public void deleteAll();
    
    @Query("SELECT mh FROM MailHistory mh WHERE mh.admin = :admin OR (mh.admin IS NULL AND :admin < '1') ORDER BY mh.sendDatetime DESC")
    public Page<MailHistory> findByAdmin(@Param("admin") Admin admin, Pageable page);

    @Query("SELECT mh FROM MailHistory mh WHERE mh.mailList = :mailList OR (mh.mailList IS NULL AND :mailList < '1') ORDER BY mh.sendDatetime DESC")
    public Page<MailHistory> findByMailList(@Param("mailList") MailList mailList, Pageable page);

    @Query("SELECT mh FROM MailHistory mh ORDER BY mh.sendDatetime DESC")
    public Page<MailHistory> findThemAll(Pageable page);

    @Query("SELECT mh FROM MailHistory mh, Admin a WHERE mh.admin.id = a.id AND LOWER(mh.subject) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(mh.body) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(mh.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.login) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY mh.sendDatetime DESC")
    public Page<MailHistory> search(@Param("searchTerm") String searchTerm, Pageable page);

}
