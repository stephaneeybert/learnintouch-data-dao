package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailAddressDao extends GenericDao<MailAddress, Serializable> {

	public Page<MailAddress> findAll(int pageNumber, int pageSize);

	public MailAddress findWithEmail(String email);

	public Page<MailAddress> findImported(int pageNumber, int pageSize);

	public Page<MailAddress> findSubscribers(int pageNumber, int pageSize);

	public Page<MailAddress> findNonSubscribers(int pageNumber, int pageSize);

	public Page<MailAddress> findSubscribersWithCountryLike(String pattern, int pageNumber, int pageSize);

	public Page<MailAddress> findWithCountryLike(String pattern, int pageNumber, int pageSize);
	
    public Page<MailAddress> findSubscriberWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<MailAddress> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<MailAddress> findWithCreationDateTimeBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize);

	public long resetImport();

	public long deleteImported();

	public long countImported();

}
