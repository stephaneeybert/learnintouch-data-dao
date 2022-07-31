package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsEditorDao;
import com.thalasoft.learnintouch.data.dao.NewsStoryDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.NewsEditorService;

@Transactional
public class NewsEditorServiceImpl implements NewsEditorService {

	@Autowired
	private NewsStoryDao newsStoryDao;
	
	@Autowired
	private NewsEditorDao newsEditorDao;

	protected void setNewsStoryDao(NewsStoryDao newsStoryDao) {
		this.newsStoryDao = newsStoryDao;
	}

	protected void setNewsEditorDao(NewsEditorDao newsEditorDao) {
		this.newsEditorDao = newsEditorDao;
	}

	@Override
	public NewsEditor save(NewsEditor newsEditor) {
		return newsEditorDao.makePersistent(newsEditor);
	}

	@Override
	public boolean isNotUsedByAnyNewsStory(NewsEditor newsEditor) {
		Page<NewsStory> page = newsStoryDao.findWithNewsEditor(newsEditor, 0, 0);
		List<NewsStory> newsStories = page.getPageItems();
		return newsStories.size() == 0;
	}
	
}
