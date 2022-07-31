package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface DocumentDao extends GenericDao<Document, Serializable> {

	public Page<Document> findAll(int pageNumber, int pageSize);

	public Page<Document> findWithCategory(DocumentCategory documentCategory, int pageNumber, int pageSize);

	public List<Document> findWithCategoryOrderById(DocumentCategory documentCategory);

	public Page<Document> findPublished(int pageNumber, int pageSize);
	
	public Document findWithListOrder(DocumentCategory documentCategory, int listOrder);

	public Document findNextWithListOrder(DocumentCategory documentCategory, int listOrder);

	public Document findPreviousWithListOrder(DocumentCategory documentCategory, int listOrder);

	public long countListOrderDuplicates(DocumentCategory documentCategory);

	public List<Document> findWithFilename(String file);

}
