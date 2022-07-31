package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.MailList;
import com.thalasoft.learnintouch.data.jpa.domain.MailListUser;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface MailListUserRepository extends GenericRepository<MailListUser, Long> {

    @Query("DELETE FROM MailListUser mlu WHERE mlu.mailList = :mailList")
    public void deleteByMailList(@Param("mailList") MailList mailList);
    
    @Query("SELECT mlu FROM MailListUser mlu, UserAccount ua WHERE mlu.userAccount.id = ua.id AND mlu.mailList = :mailList ORDER BY ua.lastname, ua.firstname")
    public Page<MailListUser> findByMailList(@Param("mailList") MailList mailList, Pageable page);
    
    @Query("SELECT mlu FROM MailListUser mlu, UserAccount ua, Address a WHERE mlu.userAccount.id = ua.id AND ua.address.id = a.id AND mlu.mailList = :mailList AND ua.mailSubscribe = '1' AND LOWER(a.country) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ua.lastname, ua.firstname")
    public Page<MailListUser> findByMailListAndSubscriberLikeCountry(@Param("mailList") MailList mailList, @Param("searchTerm") String searchTerm, Pageable page);
   
    @Query("SELECT mlu FROM MailListUser mlu WHERE mlu.userAccount = :userAccount")
    public Page<MailListUser> findByUserAccount(@Param("userAccount") UserAccount userAccount, Pageable page);

    @Query("SELECT mlu FROM MailListUser mlu WHERE mlu.mailList = :mailList AND mlu.userAccount = :userAccount")
    public MailListUser findByMailListAndUserAccount(@Param("mailList") MailList mailList, @Param("userAccount") UserAccount userAccount);

}
