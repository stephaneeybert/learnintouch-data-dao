package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonModel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningLessonModelDao extends GenericDao<ElearningLessonModel, Serializable> {

	public Page<ElearningLessonModel> findAll(int pageNumber, int pageSize);
	
}
