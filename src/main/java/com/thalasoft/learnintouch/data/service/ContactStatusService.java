package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;

public interface ContactStatusService {

	public ContactStatus save(ContactStatus contactStatus);

	public boolean isNotUsedByAnyContact(ContactStatus contactStatus);

}
