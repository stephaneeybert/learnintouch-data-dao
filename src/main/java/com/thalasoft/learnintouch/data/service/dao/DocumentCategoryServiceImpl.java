package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.DocumentCategoryDao;
import com.thalasoft.learnintouch.data.dao.DocumentDao;
import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.DocumentCategoryService;

@Transactional
public class DocumentCategoryServiceImpl implements DocumentCategoryService {

	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private DocumentCategoryDao documentCategoryDao;

	protected void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	protected void setDocumentCategoryDao(DocumentCategoryDao documentCategoryDao) {
		this.documentCategoryDao = documentCategoryDao;
	}

	@Override
	public DocumentCategory save(DocumentCategory documentCategory) {
		return documentCategoryDao.makePersistent(documentCategory);
	}
	
	@Override
	public boolean isNotUsedByAnyDocument(DocumentCategory documentCategory) {
		Page<Document> page = documentDao.findWithCategory(documentCategory, 0, 0);
		List<Document> documents = page.getPageItems();
		return documents.size() == 0;
	}
	
}
