package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.TemplatePropertySet;

public interface TemplatePropertySetRepository extends GenericRepository<TemplatePropertySet, Long> {

    @Modifying
    @Query("DELETE FROM TemplatePropertySet tps WHERE tps.id NOT IN (SELECT tm.templatePropertySet.id FROM TemplateModel tm) AND tps.id NOT IN (SELECT tm.innerTemplatePropertySet.id FROM TemplateModel tm) AND tps.id NOT IN (SELECT tc.templatePropertySet.id FROM TemplateContainer tc) AND tps.id NOT IN (SELECT tpt.templatePropertySet.id FROM TemplatePageTag tpt) AND tps.id NOT IN (SELECT tet.templatePropertySet.id from TemplateElementTag tet)")
    public void cleanup();

}
