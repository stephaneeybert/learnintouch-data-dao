package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsEditorDao extends GenericDao<NewsEditor, Serializable> {

	public NewsEditor findWithAdmin(Admin admin);

	public Page<NewsEditor> findAll(int pageNumber, int pageSize);
	
}
