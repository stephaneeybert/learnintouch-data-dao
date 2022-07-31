package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.domain.NewsStoryImage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsStoryImageDao extends GenericDao<NewsStoryImage, Serializable> {

	public NewsStoryImage findWithListOrder(NewsStory newsStory, int listOrder);
	
	public NewsStoryImage findNextWithListOrder(NewsStory newsStory, int listOrder);
	
	public NewsStoryImage findPreviousWithListOrder(NewsStory newsStory, int listOrder);
	
	public long countListOrderDuplicates(NewsStory newsStory);
	
	public Page<NewsStoryImage> findWithNewsStory(NewsStory newsStory, int pageNumber, int pageSize);
	
	public List<NewsStoryImage> findWithNewsStoryOrderById(NewsStory newsStory);
	
	public List<NewsStoryImage> findWithImage(String image);
	
}
