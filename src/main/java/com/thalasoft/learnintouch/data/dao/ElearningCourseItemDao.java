package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourseItem;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningCourseItemDao extends GenericDao<ElearningCourseItem, Serializable> {

	public Page<ElearningCourseItem> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize);
	
	public List<ElearningCourseItem> findWithCourseOrderById(ElearningCourse elearningCourse);
	
	public List<ElearningCourseItem> findWithExercise(ElearningExercise elearningExercise);

	public ElearningCourseItem findWithCourseAndExercise(ElearningCourse elearningCourse, ElearningExercise elearningExercise);
	
	public ElearningCourseItem findWithCourseAndLessonExercise(ElearningCourse elearningCourse, ElearningLesson elearningLesson, ElearningExercise elearningExercise);
	
	public ElearningCourseItem findWithCourseAndLesson(ElearningCourse elearningCourse, ElearningLesson elearningLesson);
	
	public ElearningCourseItem findWithListOrder(ElearningCourse elearningCourse, int listOrder);
	
	public ElearningCourseItem findNextWithListOrder(ElearningCourse elearningCourse, int listOrder);
	
	public ElearningCourseItem findPreviousWithListOrder(ElearningCourse elearningCourse, int listOrder);
	
	public long countListOrderDuplicates(ElearningCourse elearningCourse);
	
}
