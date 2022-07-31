package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningMatter;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningCourseDao extends GenericDao<ElearningCourse, Serializable> {

	public Page<ElearningCourse> findAll(int pageNumber, int pageSize);

	public ElearningCourse findWithName(String name);
	
	public List<ElearningCourse> findWithImage(String image);
	
	public Page<ElearningCourse> findImportable(int pageNumber, int pageSize);
	
	public Page<ElearningCourse> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize);

	public Page<ElearningCourse> findWithMatter(ElearningMatter elearningMatter, int pageNumber, int pageSize);
	
	public Page<ElearningCourse> findWithUser(UserAccount userAccount, int pageNumber, int pageSize);
	
	public Page<ElearningCourse> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);
	
}
