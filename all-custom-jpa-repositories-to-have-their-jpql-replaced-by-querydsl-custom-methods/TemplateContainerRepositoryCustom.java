package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public interface TemplateContainerRepositoryCustom {

    public Long findNbCellsByRow(TemplateModel templateModel);
    
}
