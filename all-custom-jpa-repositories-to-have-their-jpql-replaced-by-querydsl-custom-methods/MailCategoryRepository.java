package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.MailCategory;

public interface MailCategoryRepository extends GenericRepository<MailCategory, Long> {

    @Query("SELECT mc FROM MailCategory mc ORDER BY mc.name")
    public List<MailCategory> findThemAll();

}
