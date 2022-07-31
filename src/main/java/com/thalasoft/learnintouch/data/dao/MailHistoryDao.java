package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.MailHistory;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailHistoryDao extends GenericDao<MailHistory, Serializable> {

	public Page<MailHistory> findAll(int pageNumber, int pageSize);
	
	public Page<MailHistory> findWithAdmin(Admin admin, int pageNumber, int pageSize);

	public Page<MailHistory> findWithMailList(MailList mailList, int pageNumber, int pageSize);

	public Page<MailHistory> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public long deleteAll();
	
}
