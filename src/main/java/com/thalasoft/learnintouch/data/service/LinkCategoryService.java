package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;

public interface LinkCategoryService {

	public LinkCategory save(LinkCategory linkCategory);

	public boolean isNotUsedByAnyLink(LinkCategory linkCategory);

}
