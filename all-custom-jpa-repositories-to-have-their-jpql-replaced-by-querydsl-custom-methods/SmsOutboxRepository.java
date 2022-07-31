package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.SmsOutbox;

public interface SmsOutboxRepository extends GenericRepository<SmsOutbox, Long> {

    @Query("DELETE FROM SmsOutbox so")
    public void deleteAll();
    
    @Query("SELECT so FROM SmsOutbox so ORDER BY so.lastname, so.firstname")
    public Page<SmsOutbox> findThemAll(Pageable page);

    @Query("SELECT so FROM SmsOutbox so WHERE LOWER(so.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY so.lastname, so.firstname")
    public Page<SmsOutbox> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT so FROM SmsOutbox so WHERE so.sent != '1' ORDER BY so.lastname, so.firstname")
    public Page<SmsOutbox> findUnsent(Pageable page);
    
    @Query("SELECT so FROM SmsOutbox so WHERE so.sent = '1' ORDER BY so.lastname, so.firstname")
    public Page<SmsOutbox> findSent(Pageable page);

}
