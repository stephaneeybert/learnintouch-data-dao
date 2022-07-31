package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;

public interface ContactRefererService {

	public ContactReferer save(ContactReferer contactReferer);

	public boolean isNotUsedByAnyContact(ContactReferer contactReferer);

}
