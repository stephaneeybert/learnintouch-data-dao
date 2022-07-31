package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.MailCategory;

public interface MailCategoryService {

	public MailCategory save(MailCategory mailCategory);

	public boolean isNotUsedByAnyMail(MailCategory mailCategory);

}
