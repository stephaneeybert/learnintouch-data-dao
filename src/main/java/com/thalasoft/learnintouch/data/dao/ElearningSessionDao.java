package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningSessionDao extends GenericDao<ElearningSession, Serializable> {

	public Page<ElearningSession> findAll(int pageNumber, int pageSize);

	public Page<ElearningSession> findWithName(String name, int pageNumber, int pageSize);
	
	public Page<ElearningSession> findNotYetOpened(LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningSession> findCurrentlyOpened(LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningSession> findClosed(LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningSession> findNotClosed(LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
}
