package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Sms;
import com.thalasoft.learnintouch.data.jpa.domain.Admin;
import com.thalasoft.learnintouch.data.jpa.domain.SmsHistory;
import com.thalasoft.learnintouch.data.jpa.domain.SmsList;

public interface SmsHistoryRepository extends GenericRepository<SmsHistory, Long> {

    @Query("DELETE FROM SmsHistory sh")
    public void deleteAll();
    
    @Query("SELECT sh FROM SmsHistory sh WHERE sh.admin = :admin OR (sh.admin IS NULL AND :admin < '1') ORDER BY sh.sendDatetime DESC")
    public Page<SmsHistory> findByAdmin(@Param("admin") Admin admin, Pageable page);

    @Query("SELECT sh FROM SmsHistory sh ORDER BY sh.sendDatetime DESC")
    public Page<SmsHistory> findThemAll(Pageable page);
    
    @Query("SELECT sh FROM SmsHistory sh WHERE sh.smsList = :smsList OR (sh.smsList IS NULL AND :smsList < '1') ORDER BY sh.sendDatetime DESC")
    public Page<SmsHistory> findBySmsList(@Param("smsList") SmsList smsList, Pageable page);

    @Query("SELECT sh FROM SmsHistory sh WHERE sh.sms = :sms OR (sh.sms IS NULL AND :sms < '1') ORDER BY sh.sendDatetime DESC")
    public Page<SmsHistory> findBySms(@Param("sms") Sms sms, Pageable page);

}
