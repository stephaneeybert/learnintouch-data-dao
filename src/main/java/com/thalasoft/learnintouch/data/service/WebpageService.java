package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.Webpage;

public interface WebpageService {

	public Webpage save(Webpage webpage);

	public boolean isNotUsedByAnyChild(Webpage webpage);

}
