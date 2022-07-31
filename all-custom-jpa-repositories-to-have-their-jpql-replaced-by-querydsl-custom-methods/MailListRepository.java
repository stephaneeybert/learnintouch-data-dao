package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.MailList;

public interface MailListRepository extends GenericRepository<MailList, Long> {

    @Query("SELECT ml FROM MailList ml ORDER BY ml.name")
    public Page<MailList> findThemAll(Pageable page);

    @Query("SELECT ml FROM MailList ml WHERE ml.autoSubscribe = '1' ORDER BY ml.name")
    public Page<MailList> findByAutoSubscribe(Pageable page);

    @Query("SELECT ml FROM MailList ml WHERE LOWER(ml.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ml.name")
    public Page<MailList> search(@Param("searchTerm") String searchTerm, Pageable page);

}
