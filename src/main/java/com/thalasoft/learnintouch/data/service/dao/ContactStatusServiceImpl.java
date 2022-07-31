package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.ContactStatusDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.ContactStatusService;

@Transactional
public class ContactStatusServiceImpl implements ContactStatusService {

	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private ContactStatusDao contactStatusDao;

	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	protected void setContactStatusDao(ContactStatusDao contactStatusDao) {
		this.contactStatusDao = contactStatusDao;
	}

	@Override
	public ContactStatus save(ContactStatus contactStatus) {
		return contactStatusDao.makePersistent(contactStatus);
	}
	
	@Override
	public boolean isNotUsedByAnyContact(ContactStatus contactStatus) {
		Page<Contact> page = contactDao.findWithStatus(contactStatus, 0, 0);
		List<Contact> contacts = page.getPageItems(); 
		return contacts.size() == 0;
	}
	
}

