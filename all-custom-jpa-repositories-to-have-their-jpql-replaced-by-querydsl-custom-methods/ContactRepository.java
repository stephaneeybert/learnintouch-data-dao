package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.jpa.domain.Contact;
import com.thalasoft.learnintouch.data.jpa.domain.ContactReferer;

public interface ContactRepository extends GenericRepository<Contact, Long> {

    @Query("DELETE FROM Contact c WHERE c.contactDatetime IS NOT NULL AND c.contactDatetime <= :contactDatetime")
    public void deleteByDatetime(@Param("contactDatetime") String contactDatetime);

    @Query("SELECT c FROM Contact c ORDER BY c.contactDatetime DESC")
    public Page<Contact> findThemAll(Pageable page);

    @Query("SELECT c FROM Contact c WHERE c.garbage = '1' ORDER BY c.contactDatetime DESC")
    public Page<Contact> findGarbage(Pageable page);
    
    @Query("SELECT c FROM Contact c WHERE c.garbage != '1' ORDER BY c.contactDatetime DESC")
    public Page<Contact> findNonGarbage(Pageable page);

    @Query("SELECT c FROM Contact c WHERE c.garbage != '1' AND (c.contactStatus = :contactStatus OR (c.contactStatus IS NULL AND :contactStatus < '1')) ORDER BY c.contactDatetime DESC")
    public Page<Contact> findByStatus(ContactStatus contactStatus, Pageable page);

    @Query("SELECT c FROM Contact c WHERE (c.contactStatus = :contactStatus OR (c.contactStatus IS NULL AND :contactStatus < '1')) ORDER BY c.contactDatetime DESC")
    public List<Contact> findAllByStatus(ContactStatus contactStatus);

    @Query("SELECT c FROM Contact c WHERE c.garbage != '1' AND (c.contactReferer = :contactReferer OR (c.contactReferer IS NULL AND :contactReferer < '1')) ORDER BY c.contactDatetime DESC")
    public Page<Contact> findByReferer(ContactReferer contactReferer, Pageable page);

    @Query("SELECT c FROM Contact c WHERE (c.contactReferer = :contactReferer OR (c.contactReferer IS NULL AND :contactReferer < '1')) ORDER BY c.contactDatetime DESC")
    public List<Contact> findAllByReferer(ContactReferer contactReferer);

}
