package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface DocumentCategoryDao extends GenericDao<DocumentCategory, Serializable> {

	public Page<DocumentCategory> findAll(int pageNumber, int pageSize);

	public List<DocumentCategory> findAllOrderById();

	public DocumentCategory findWithListOrder(int listOrder);
	
	public DocumentCategory findNextWithListOrder(int listOrder);

	public DocumentCategory findPreviousWithListOrder(int listOrder);

	public long countListOrderDuplicates();

}
