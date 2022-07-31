package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ContactDao extends GenericDao<Contact, Serializable> {

	public Page<Contact> findAll(int pageNumber, int pageSize);

	public Page<Contact> findInGarbage(int pageNumber, int pageSize);

	public Page<Contact> findNotInGarbage(int pageNumber, int pageSize);

	public Page<Contact> findWithStatus(ContactStatus contactStatus, int pageNumber, int pageSize);

	public Page<Contact> findNotInGarbageByStatus(ContactStatus contactStatus, int pageNumber, int pageSize);
	
	public Page<Contact> findWithReferer(ContactReferer contactReferer, int pageNumber, int pageSize);

	public long deleteByDate(LocalDateTime sinceDate);

}
