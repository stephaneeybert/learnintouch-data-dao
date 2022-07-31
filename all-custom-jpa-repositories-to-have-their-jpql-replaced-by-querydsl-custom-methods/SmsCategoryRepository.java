package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.SmsCategory;

public interface SmsCategoryRepository extends GenericRepository<SmsCategory, Long> {

    @Query("SELECT sc FROM SmsCategory sc ORDER BY sc.name")
    public Page<SmsCategory> findThemAll(Pageable page);

}
