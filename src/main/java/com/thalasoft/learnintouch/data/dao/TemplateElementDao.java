package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;

public interface TemplateElementDao extends GenericDao<TemplateElement, Serializable> {

	public List<TemplateElement> findWithContainer(TemplateContainer templateContainer);

	public List<TemplateElement> findWithContainerOrderById(TemplateContainer templateContainer);
	
	public TemplateElement findWithListOrder(TemplateContainer templateContainer, int listOrder);
	
	public TemplateElement findNextWithListOrder(TemplateContainer templateContainer, int listOrder);
	
	public TemplateElement findPreviousWithListOrder(TemplateContainer templateContainer, int listOrder);
	
	public long countListOrderDuplicates(TemplateContainer templateContainer);
	
}
