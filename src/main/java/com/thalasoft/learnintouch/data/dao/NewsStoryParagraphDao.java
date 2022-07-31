package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.domain.NewsStoryParagraph;

public interface NewsStoryParagraphDao extends GenericDao<NewsStoryParagraph, Serializable> {

	public List<NewsStoryParagraph> findWithNewsStory(NewsStory newsStory);
	
}
