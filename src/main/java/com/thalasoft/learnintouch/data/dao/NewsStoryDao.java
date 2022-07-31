package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;
import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;
import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsStoryDao extends GenericDao<NewsStory, Serializable> {

	public NewsStory findWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder);

	public NewsStory findNextWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder);

	public NewsStory findPreviousWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder);

	public long countListOrderDuplicates(NewsPaper newsPaper, NewsHeading newsHeading);

	public Page<NewsStory> findWithNewsPaper(NewsPaper newsPaper, int pageNumber, int pageSize);

	public Page<NewsStory> findWithNewsHeading(NewsHeading newsHeading, int pageNumber, int pageSize);

	public Page<NewsStory> findWithNewsEditor(NewsEditor newsEditor, int pageNumber, int pageSize);

	public Page<NewsStory> findWithNewsPaperAndNewsHeading(NewsPaper newsPaper, NewsHeading newsHeading, int pageNumber, int pageSize);
	
	public List<NewsStory> findWithNewsPaperAndNewsHeadingOrderById(NewsPaper newsPaper, NewsHeading newsHeading);
	
	public Page<NewsStory> findWithNewsPaperAndNewsEditor(NewsPaper newsPaper, NewsEditor newsEditor, int pageNumber, int pageSize);

	public Page<NewsStory> findWithNewsHeadingAndNewsEditor(NewsHeading newsHeading, NewsEditor newsEditor, int pageNumber, int pageSize);
	
	public Page<NewsStory> findWithNewsPaperAndNewsHeadingAndNewsEditor(NewsPaper newsPaper, NewsHeading newsHeading, NewsEditor newsEditor, int pageNumber, int pageSize);
	
	public Page<NewsStory> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public List<NewsStory> findWithAudio(String audio);

	public Page<NewsStory> findWithNewsPaperAndCurrent(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<NewsStory> findWithNewsPaperAndArchived(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<NewsStory> findWithNewsPaperAndDeferred(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
}
