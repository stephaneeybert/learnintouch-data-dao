package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonHeading;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonModel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningLessonHeadingDao extends GenericDao<ElearningLessonHeading, Serializable> {

	public Page<ElearningLessonHeading> findAll(int pageNumber, int pageSize);
	
	public Page<ElearningLessonHeading> findWithLessonModel(ElearningLessonModel elearningLessonModel, int pageNumber, int pageSize);
	
	public List<ElearningLessonHeading> findWithLessonModelOrderById(ElearningLessonModel elearningLessonModel);
	
	public List<ElearningLessonHeading> findWithImage(String image);
	
	public ElearningLessonHeading findWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder);

	public ElearningLessonHeading findNextWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder);

	public ElearningLessonHeading findPreviousWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder);

	public long countListOrderDuplicates(ElearningLessonModel elearningLessonModel);
	
}
