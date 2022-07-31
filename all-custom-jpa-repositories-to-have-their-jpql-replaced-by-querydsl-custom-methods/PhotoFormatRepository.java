package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.PhotoFormat;

public interface PhotoFormatRepository extends GenericRepository<PhotoFormat, Long> {

    @Query("SELECT pf FROM PhotoFormat pf ORDER BY pf.name")
    public Page<PhotoFormat> findThemAll(Pageable page);

    public PhotoFormat findByName(String name);

}
