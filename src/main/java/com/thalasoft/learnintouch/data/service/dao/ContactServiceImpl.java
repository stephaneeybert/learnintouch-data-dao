package com.thalasoft.learnintouch.data.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.service.ContactService;

@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	
	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Override
	public Contact save(Contact contact) {
		return contactDao.makePersistent(contact);
	}

}
