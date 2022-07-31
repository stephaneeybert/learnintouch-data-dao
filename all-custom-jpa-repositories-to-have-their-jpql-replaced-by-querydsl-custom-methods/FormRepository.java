package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.Form;

public interface FormRepository extends GenericRepository<Form, Long> {

    @Query("SELECT f FROM Form f ORDER BY f.name")
    public Page<Form> findThemAll(Pageable page);

    public Form findByName(String name);

    public List<Form> findByImage(String image);

}
