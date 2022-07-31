package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public interface TemplateContainerRepository extends GenericRepository<TemplateContainer, Long> {

    @Query("SELECT tc FROM TemplateContainer tc WHERE tc.templateModel = :templateModel ORDER BY tc.rowNb, tc.cellNb")
    public List<TemplateContainer> findByModel(@Param("templateModel") TemplateModel templateModel);

    @Query("SELECT tc FROM TemplateContainer tc WHERE tc.templateModel = :templateModel AND tc.rowNb = :rowNb ORDER BY tc.cellNb")
    public List<TemplateContainer> findByModelAndRow(@Param("templateModel") TemplateModel templateModel, @Param("rowNb") int rowNb);

    @Query("SELECT tc FROM TemplateContainer tc WHERE tc.templateModel = :templateModel AND tc.rowNb = :rowNb AND tc.cellNb = :cellNb")
    public TemplateContainer findByModelAndRowAndCell(@Param("templateModel") TemplateModel templateModel, @Param("rowNb") int rowNb, @Param("cellNb") int cellNb);

    @Query("SELECT tc FROM TemplateContainer tc WHERE tc.templateModel = :templateModel AND tc.rowNb = :rowNb AND tc.cellNb > :cellNb ORDER BY tc.cellNb ASC LIMIT 1")
    public TemplateContainer findByNextCell(@Param("templateModel") TemplateModel templateModel, @Param("rowNb") int rowNb, @Param("cellNb") int cellNb);

    @Query("SELECT tc FROM TemplateContainer tc WHERE tc.templateModel = :templateModel AND tc.rowNb = :rowNb AND tc.cellNb < :cellNb ORDER BY tc.cellNb DESC LIMIT 1")
    public TemplateContainer findByPreviousCell(@Param("templateModel") TemplateModel templateModel, @Param("rowNb") int rowNb, @Param("cellNb") int cellNb);

}
