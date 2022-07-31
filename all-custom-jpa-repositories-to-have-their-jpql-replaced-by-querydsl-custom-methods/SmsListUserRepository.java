package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.SmsList;
import com.thalasoft.learnintouch.data.jpa.domain.SmsListUser;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface SmsListUserRepository extends GenericRepository<SmsListUser, Long> {

    @Query("DELETE FROM SmsListUser slu WHERE slu.smsList = :smsList")
    public void deleteBySmsList(@Param("smsList") SmsList smsList);
    
    @Query("SELECT slu FROM SmsListUser slu, UserAccount ua WHERE slu.userAccount.id = ua.id AND slu.smsList = :smsList ORDER BY ua.lastname, ua.firstname")
    public Page<SmsListUser> findBySmsList(@Param("smsList") SmsList smsList, Pageable page);
    
    @Query("SELECT slu FROM SmsListUser slu, UserAccount ua, Address a WHERE slu.userAccount.id = ua.id AND ua.address.id = a.id AND slu.smsList = :smsList AND ua.smsSubscribe = '1' AND LOWER(a.country) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ua.lastname, ua.firstname")
    public Page<SmsListUser> findBySmsListAndSubscriberLikeCountry(@Param("smsList") SmsList smsList, @Param("searchTerm") String searchTerm, Pageable page);
   
    @Query("SELECT slu FROM SmsListUser slu WHERE slu.userAccount = :userAccount")
    public Page<SmsListUser> findByUserAccount(@Param("userAccount") UserAccount userAccount, Pageable page);

    @Query("SELECT slu FROM SmsListUser slu WHERE slu.smsList = :smsList AND slu.userAccount = :userAccount")
    public SmsListUser findBySmsListAndUserAccount(@Param("smsList") SmsList smsList, @Param("userAccount") UserAccount userAccount);

}
