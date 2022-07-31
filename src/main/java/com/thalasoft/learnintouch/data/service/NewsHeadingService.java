package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;

public interface NewsHeadingService {

	public NewsHeading save(NewsHeading newsHeading);

	public boolean isNotUsedByAnyNewsStory(NewsHeading newsHeading);

}
