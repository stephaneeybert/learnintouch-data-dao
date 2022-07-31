package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningTeacherDao extends GenericDao<ElearningTeacher, Serializable> {

	public Page<ElearningTeacher> findAll(int pageNumber, int pageSize);

	public Page<ElearningTeacher> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public ElearningTeacher findWithUser(UserAccount userAccount);
	
}
