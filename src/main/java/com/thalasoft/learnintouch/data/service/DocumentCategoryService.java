package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;

public interface DocumentCategoryService {

	public DocumentCategory save(DocumentCategory documentCategory);

	public boolean isNotUsedByAnyDocument(DocumentCategory documentCategory);

}
