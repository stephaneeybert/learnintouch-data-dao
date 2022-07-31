package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.ContactRefererDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.ContactRefererService;

@Transactional
public class ContactRefererServiceImpl implements ContactRefererService {

	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private ContactRefererDao contactRefererDao;
	
	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	protected void setContactRefererDao(ContactRefererDao contactRefererDao) {
		this.contactRefererDao = contactRefererDao;
	}

	@Override
	public ContactReferer save(ContactReferer contactReferer) {
		return contactRefererDao.makePersistent(contactReferer);
	}
	
	@Override
	public boolean isNotUsedByAnyContact(ContactReferer contactReferer) {
		Page<Contact> page = contactDao.findWithReferer(contactReferer, 0, 0);
		List<Contact> contacts = page.getPageItems();
		return contacts.size() == 0;
	}
	
}

