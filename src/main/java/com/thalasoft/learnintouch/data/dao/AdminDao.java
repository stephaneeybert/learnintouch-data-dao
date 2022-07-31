package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface AdminDao extends GenericDao<Admin, Serializable> {

	public Admin findWithEmail(String email);

	public Admin findWithLogin(String login);

	public Admin findWithLoginAndPassword(String login, String password);

	public Page<Admin> findAll(int pageNumber, int pageSize);
	
	public Page<Admin> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<Admin> findAllNonSuperAdminPlusSpecifiedOne(String login, int pageNumber, int pageSize);

}
