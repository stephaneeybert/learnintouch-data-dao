package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSessionCourse;

public interface ElearningSessionCourseDao extends GenericDao<ElearningSessionCourse, Serializable> {

	public long deleteWithSession(ElearningSession elearningSession);

	public List<ElearningSessionCourse> findWithSession(ElearningSession elearningSession);

	public List<ElearningSessionCourse> findWithCourse(ElearningCourse elearningCourse);
	
	public ElearningSessionCourse findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse);
	
}
