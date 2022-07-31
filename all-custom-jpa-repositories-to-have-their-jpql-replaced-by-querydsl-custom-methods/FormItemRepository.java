package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Form;
import com.thalasoft.learnintouch.data.jpa.domain.FormItem;

public interface FormItemRepository extends GenericRepository<FormItem, Long> {

    @Query("SELECT fi FROM FormItem fi WHERE fi.form = :form ORDER BY fi.listOrder")
    public List<FormItem> findByForm(@Param("form") Form form);

    @Query("SELECT fi FROM FormItem fi WHERE fi.form = :form ORDER BY fi.id")
    public List<FormItem> findByFormOrderById(@Param("form") Form form);

    @Query("SELECT fi FROM FormItem fi WHERE fi.listOrder > :listOrder ORDER BY fi.listOrder ASC LIMIT 1")
    public FormItem findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT fi FROM FormItem fi WHERE fi.listOrder < :listOrder ORDER BY fi.listOrder DESC LIMIT 1")
    public FormItem findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT fi FROM FormItem fi WHERE fi.listOrder = :listOrder")
    public List<FormItem> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT fi1.id) as count FROM FormItem fi1, FormItem fi2 WHERE fi1.id != fi2.id AND fi1.form.id = fi2.form.id AND fi1.listOrder = fi2.listOrder AND fi1.form = :form")
    public Long countDuplicateListOrders(@Param("form") Form form);

}
