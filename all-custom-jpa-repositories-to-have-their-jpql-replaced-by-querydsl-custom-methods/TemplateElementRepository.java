package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateElement;

public interface TemplateElementRepository extends GenericRepository<TemplateElement, Long> {

    @Query("SELECT te FROM TemplateElement te WHERE te.templateContainer = :templateContainer ORDER BY te.listOrder")
    public List<TemplateElement> findByContainer(@Param("templateContainer") TemplateContainer templateContainer);

    @Query("SELECT te FROM TemplateElement te WHERE te.templateContainer = :templateContainer ORDER BY te.id")
    public List<TemplateElement> findByContainerOrderById(@Param("templateContainer") TemplateContainer templateContainer);

    @Query("SELECT te FROM TemplateElement te WHERE (te.templateContainer = :shopCategory OR (te.templateContainer IS NULL AND :templateContainer < '1')) AND te.listOrder > :listOrder ORDER BY te.listOrder ASC LIMIT 1")
    public TemplateElement findByNextListOrder(@Param("templateContainer") TemplateContainer templateContainer, @Param("listOrder") int listOrder);

    @Query("SELECT te FROM TemplateElement te WHERE (te.templateContainer = :shopCategory OR (te.templateContainer IS NULL AND :templateContainer < '1')) AND te.listOrder < :listOrder ORDER BY te.listOrder DESC LIMIT 1")
    public TemplateElement findByPreviousListOrder(@Param("templateContainer") TemplateContainer templateContainer, @Param("listOrder") int listOrder);

    @Query("SELECT te FROM TemplateElement te WHERE (te.templateContainer = :shopCategory OR (te.templateContainer IS NULL AND :templateContainer < '1')) AND te.listOrder = :listOrder ORDER BY te.listOrder DESC")
    public List<TemplateElement> findByListOrder(@Param("templateContainer") TemplateContainer templateContainer, @Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT te1.id) as count FROM TemplateElement te1, TemplateElement te2 WHERE te1.id != te2.id AND te1.templateContainer.id = te2.templateContainer.id AND te1.listOrder = te2.listOrder AND te1.templateContainer = :templateContainer")
    public Long countDuplicateListOrders(@Param("templateContainer") TemplateContainer templateContainer);

}
