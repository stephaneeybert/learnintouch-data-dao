package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.FormItem;
import com.thalasoft.learnintouch.data.jpa.domain.FormItemValue;

public interface FormItemValueRepository extends GenericRepository<FormItemValue, Long> {

    @Query("SELECT fiv FROM FormItemValue fiv WHERE fiv.formItem = :formItem")
    public List<FormItemValue> findByFormItem(@Param("formItem") FormItem formItem);

}
