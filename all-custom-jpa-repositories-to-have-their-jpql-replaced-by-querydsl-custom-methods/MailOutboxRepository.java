package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.MailOutbox;

public interface MailOutboxRepository extends GenericRepository<MailOutbox, Long> {

    @Query("DELETE FROM MailOutbox mo")
    public void deleteAll();
    
    @Query("SELECT mo FROM MailOutbox mo ORDER BY mo.email")
    public Page<MailOutbox> findThemAll(Pageable page);

    @Query("SELECT mo FROM MailOutbox mo WHERE LOWER(mo.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(mo.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(mo.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY mo.email")
    public Page<MailOutbox> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT mo FROM MailOutbox mo WHERE mo.sent != '1' ORDER BY mo.email")
    public Page<MailOutbox> findUnsent(Pageable page);
    
    @Query("SELECT mo FROM MailOutbox mo WHERE mo.sent = '1' ORDER BY mo.email")
    public Page<MailOutbox> findSent(Pageable page);

}
