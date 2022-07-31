package com.thalasoft.learnintouch.data.jpa.repository;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public class TemplateContainerRepositoryImpl implements TemplateContainerRepositoryCustom {

    @Autowired
    private TemplateContainerRepository templateContainerRepository;

    public Long findNbCellsByRow(TemplateModel templateModel) {
        String sqlStatement = "SELECT rowNb, (MAX(cellNb)+1) AS nbCells FROM TemplateContainer tc WHERE tc.templateModel = :templateModel GROUP BY rowNb";
        
        TypedQuery<Long> query = templateContainerRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("templateModel", templateModel);

        Long count = query.getSingleResult();
        
        return count;
    }

}
