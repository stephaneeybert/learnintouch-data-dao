package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NewsFeed;
import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;

public interface NewsFeedDao extends GenericDao<NewsFeed, Serializable> {

	public NewsFeed findWithNewsPaper(NewsPaper newsPaper);

	public List<NewsFeed> findWithImage(String image);
	
}
