package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.MailAddress;
import com.thalasoft.learnintouch.data.jpa.domain.MailList;
import com.thalasoft.learnintouch.data.jpa.domain.MailListAddress;

public interface MailListAddressRepository extends GenericRepository<MailListAddress, Long> {

    @Query("DELETE FROM MailListAddress mla WHERE mla.mailList = :mailList")
    public void deleteByMailList(@Param("mailList") MailList mailList);
    
    @Query("SELECT mla FROM MailListAddress mla, MailAddress ma WHERE mla.mailAddress.id = ma.id AND mla.mailList = :mailList ORDER BY ma.email")
    public Page<MailListAddress> findByMailList(@Param("mailList") MailList mailList, Pageable page);
    
    @Query("SELECT mla FROM MailListAddress mla, MailAddress ma WHERE mla.mailAddress.id = ma.id AND mla.mailList = :mailList AND ma.subscribe = '1' AND LOWER(ma.country) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ma.email")
    public Page<MailListAddress> findByMailListAndSubscriberLikeCountry(@Param("mailList") MailList mailList, @Param("searchTerm") String searchTerm, Pageable page);
   
    @Query("SELECT mla FROM MailListAddress mla WHERE mla.mailAddress = :mailAddress")
    public Page<MailListAddress> findByMailAddress(@Param("mailAddress") MailAddress mailAddress, Pageable page);

    @Query("SELECT mla FROM MailListAddress mla WHERE mla.mailList = :mailList AND mla.mailAddress = :mailAddress")
    public MailListAddress findByMailListAndMailAddress(@Param("mailList") MailList mailList, @Param("mailAddress") MailAddress mailAddress);

}
