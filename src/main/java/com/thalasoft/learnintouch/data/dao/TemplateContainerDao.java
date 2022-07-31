package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public interface TemplateContainerDao extends GenericDao<TemplateContainer, Serializable> {

	public List<TemplateContainer> findWithModel(TemplateModel templateModel);
	
	public List<TemplateContainer> findWithModelAndRow(TemplateModel templateModel, int row);

	public TemplateContainer findWithModelAndRowAndCell(TemplateModel templateModel, int row, int cell);

	public TemplateContainer findWithModelAndNextCell(TemplateModel templateModel, int row, int cell);
	
	public TemplateContainer findWithModelAndPreviousCell(TemplateModel templateModel, int row, int cell);
	
	public int findNbCellsByRow(TemplateModel templateModel);
	
}
