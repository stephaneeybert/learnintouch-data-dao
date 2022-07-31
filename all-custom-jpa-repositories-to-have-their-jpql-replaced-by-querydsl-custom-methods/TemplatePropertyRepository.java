package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateProperty;
import com.thalasoft.learnintouch.data.jpa.domain.TemplatePropertySet;

public interface TemplatePropertyRepository extends GenericRepository<TemplateProperty, Long> {

    public TemplateProperty findByValue(String value);

    @Query("SELECT tp FROM TemplateProperty tp WHERE (tp.templatePropertySet = :templatePropertySet OR (tp.templatePropertySet IS NULL AND :templatePropertySet < '1')) AND tp.name = :name")
    public TemplateProperty findByPropertySetAndName(@Param("templatePropertySet") TemplatePropertySet templatePropertySet, @Param("name") String name);

    @Query("SELECT tp FROM TemplateProperty tp WHERE (tp.templatePropertySet = :templatePropertySet OR (tp.templatePropertySet IS NULL AND :templatePropertySet < '1')) ORDER BY tp.name")
    public List<TemplateProperty> findByPropertySet(@Param("templatePropertySet") TemplatePropertySet templatePropertySet);

    @Modifying
    @Query("DELETE FROM TemplateProperty tp WHERE tp.templatePropertySet.id NOT IN (SELECT tps.id FROM TemplatePropertySet tps)")
    public void cleanup();

}
