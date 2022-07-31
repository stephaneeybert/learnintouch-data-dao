package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.FormItem;
import com.thalasoft.learnintouch.data.jpa.domain.FormValid;

public interface FormValidRepository extends GenericRepository<FormValid, Long> {

    @Query("SELECT fv FROM FormValid fv WHERE fv.formItem = :formItem")
    public List<FormValid> findByFormItem(@Param("formItem") FormItem formItem);

}
