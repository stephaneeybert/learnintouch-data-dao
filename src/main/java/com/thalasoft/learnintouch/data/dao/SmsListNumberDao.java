package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.domain.SmsListNumber;
import com.thalasoft.learnintouch.data.dao.domain.SmsNumber;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsListNumberDao extends GenericDao<SmsListNumber, Serializable> {

	public Page<SmsListNumber> findWithSmsList(SmsList smsList, int pageNumber, int pageSize);

	public Page<SmsListNumber> findWithSmsNumber(SmsNumber smsNumber, int pageNumber, int pageSize);

	public SmsListNumber findWithSmsListAndSmsNumber(SmsList smsList, SmsNumber smsNumber);
	
	public long deleteBySmsList(SmsList smsList);
	
}
