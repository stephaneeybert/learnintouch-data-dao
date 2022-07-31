package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.SmsList;
import com.thalasoft.learnintouch.data.jpa.domain.SmsListNumber;
import com.thalasoft.learnintouch.data.jpa.domain.SmsNumber;

public interface SmsListNumberRepository extends GenericRepository<SmsListNumber, Long> {

    @Query("DELETE FROM SmsListNumber sln WHERE sln.smsList = :smsList")
    public void deleteBySmsList(@Param("smsList") SmsList smsList);
    
    @Query("SELECT sln FROM SmsListNumber sln, SmsNumber sn WHERE sln.smsNumber.id = sn.id AND sln.smsList = :smsList ORDER BY sn.lastname, sn.firstname")
    public Page<SmsListNumber> findBySmsList(@Param("smsList") SmsList smsList, Pageable page);
    
    @Query("SELECT sln FROM SmsListNumber sln WHERE sln.smsNumber = :smsNumber")
    public Page<SmsListNumber> findBySmsNumber(@Param("smsNumber") SmsNumber smsNumber, Pageable page);

    @Query("SELECT sln FROM SmsListNumber sln WHERE sln.smsList = :smsList AND sln.smsNumber = :smsNumber")
    public SmsListNumber findBySmsListAndSmsNumber(@Param("smsList") SmsList smsList, @Param("smsNumber") SmsNumber smsNumber);

}
