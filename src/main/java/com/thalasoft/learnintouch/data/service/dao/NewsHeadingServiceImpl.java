package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsHeadingDao;
import com.thalasoft.learnintouch.data.dao.NewsStoryDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.NewsHeadingService;

@Transactional
public class NewsHeadingServiceImpl implements NewsHeadingService {

	@Autowired
	private NewsStoryDao newsStoryDao;
	
	@Autowired
	private NewsHeadingDao newsHeadingDao;

	protected void setNewsStoryDao(NewsStoryDao newsStoryDao) {
		this.newsStoryDao = newsStoryDao;
	}

	protected void setNewsHeadingDao(NewsHeadingDao newsHeadingDao) {
		this.newsHeadingDao = newsHeadingDao;
	}

	@Override
	public NewsHeading save(NewsHeading newsHeading) {
		return newsHeadingDao.makePersistent(newsHeading);
	}

	@Override
	public boolean isNotUsedByAnyNewsStory(NewsHeading newsHeading) {
		Page<NewsStory> page = newsStoryDao.findWithNewsHeading(newsHeading, 0, 0);
		List<NewsStory> newsStories = page.getPageItems();
		return newsStories.size() == 0;
	}
	
}
